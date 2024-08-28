package com.sml.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sml.model.ChatVO;
import com.sml.model.MemberVO;
import com.sml.model.UserSessionInfo;
import com.sml.service.AdminService;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class AdminChatController {

	private static final Logger logger = Logger.getLogger(AdminChatController.class.getName());

	private static final Map<String, UserSessionInfo> sessions = new ConcurrentHashMap<>();
	private static final Map<String, StringBuilder> chatBuffers = new ConcurrentHashMap<>(); // 대화 내용을 저장할 버퍼
	private static final Map<String, Session> adminSessions = new ConcurrentHashMap<>();

	private static AdminService service;

	@Autowired
	public void setAdminService(AdminService adminService) {
		service = adminService;
	}

	@OnOpen
	public void onOpen(Session session) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");

		if (httpSession != null) {
			MemberVO member = (MemberVO) httpSession.getAttribute("member");
			if (member != null) {
				String userId = member.getMemId();
				int memCode = member.getMemCode();
				String memName = member.getMemName();
				int memAdminCheck = member.getMemAdminCheck();
				String chatSessionId = session.getId();
				String conversationId = "conv_common"; // 공통 대화 ID 사용

				UserSessionInfo sessionInfo = new UserSessionInfo(userId, session, memName, memCode, conversationId);
				sessions.put(userId, sessionInfo);
				chatBuffers.putIfAbsent(conversationId, new StringBuilder()); // 대화 버퍼가 없으면 생성
				logger.info("연결된 회원: " + userId + " / memAdminCheck : " + memAdminCheck);

				if (memAdminCheck == 1) {
					adminSessions.put(userId, session);
					logger.info("관리자 로그인: " + userId);
				} else {
					try {
						session.getBasicRemote().sendText("안녕하세요, " + userId + " 님!");
						session.getBasicRemote().sendText("무엇을 도와드릴까요?");
						for (Session adminSession : adminSessions.values()) {
							adminSession.getBasicRemote().sendText(userId + " 님이 접속하였습니다.");
						}
					} catch (IOException e) {
						logger.severe("환영메시지 발송 실패 : " + e.getMessage());
					}
				}
			} else {
				logger.warning("회원 정보가 존재하지 않습니다.");
			}
		} else {
			logger.warning("HTTP 세션이 null입니다.");
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
		MemberVO member = (MemberVO) httpSession.getAttribute("member");

		try {
			if (message.trim().startsWith("{")) {
				JSONObject jsonMessage = new JSONObject(message);
				logger.info("수신된 JSON 메시지: " + jsonMessage.toString());

				String userId = member.getMemId();
				String memName = member.getMemName();
				String content = jsonMessage.getString("content");

				// 대화 ID를 가져옴
				UserSessionInfo sessionInfo = sessions.get(userId);
				String conversationId = sessionInfo != null ? sessionInfo.getConversationId() : "conv_common";

				if (conversationId == null) {
					logger.warning("대화 ID가 null입니다. 메시지 저장 실패.");
					return;
				}

				// 해당 대화 ID의 채팅 버퍼에 메시지 추가
				StringBuilder buffer = chatBuffers.get(conversationId);
				if (buffer != null) {
					Date currentTime = new Date();
					String formattedTime = new SimpleDateFormat("HH:mm:ss").format(currentTime);
					buffer.append(memName + " : " + content + " (" + formattedTime + ") // \n  ");
				} else {
					logger.warning("해당 대화 ID의 채팅 버퍼를 찾을 수 없습니다.");
				}

				// 모든 연결된 세션에 메시지 전송
				JSONObject responseMessage = new JSONObject();
				responseMessage.put("userId", userId);
				responseMessage.put("memName", memName);
				responseMessage.put("content", content);
				responseMessage.put("timestamp", System.currentTimeMillis());

				for (UserSessionInfo info : sessions.values()) {
					Session s = info.getSession();
					if (s.isOpen()) {
						s.getBasicRemote().sendText(responseMessage.toString());
					}
				}
			} else {
				logger.warning("수신된 메시지가 JSON 형식 아님 : " + message);
			}
		} catch (JSONException e) {
			logger.severe("메시지 처리 중 JSON 오류 발생: " + message);
		} catch (IOException e) {
			logger.severe("클라이언트로 메시지 전송 중 오류 발생: " + e.getMessage());
		}
	}

	@OnClose
	public void onClose(Session session) {
		String userId = null;
		MemberVO member = null;
		String conversationId = "conv_common"; // 공통 대화 ID 사용

		for (Map.Entry<String, UserSessionInfo> entry : sessions.entrySet()) {
			if (entry.getValue().getSession().equals(session)) {
				userId = entry.getKey();
				HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
				if (httpSession != null) {
					member = (MemberVO) httpSession.getAttribute("member");
				}
				break;
			}
		}

		if (userId != null) {
			// 대화 ID에 해당하는 채팅 내용을 저장
			StringBuilder buffer = chatBuffers.remove(conversationId);
			if (buffer != null) {
				ChatVO chat = new ChatVO();
				chat.setConversationId(conversationId); // 공통 대화 ID 설정
				int categoryCode = 1;
				chat.setCategoryCode(categoryCode);
				String chatContent = buffer.toString();
				chat.setChatContent(chatContent);
				int memCode = member.getMemCode();
				chat.setMemCode(memCode);
				Date chatDate = new Date();
				chat.setChatDate(chatDate);
				int status = 1;
				chat.setStatus(status);
				logger.info("chatVO : " + chat.toString());
				try {
					service.saveChatContent(chat);
					logger.info("채팅 내용 저장됨: " + chat.toString());
				} catch (Exception e) {
					logger.severe("service가 유효하지 않음, 채팅 내용 저장 실패: " + e.getMessage());
				}
			} else {
				logger.warning("채팅 버퍼가 비어 있습니다. 세션 종료 시 채팅 내용이 없습니다.");
			}

			// 관리자에게 사용자 종료 알림 전송
			if (member != null && member.getMemAdminCheck() != 1) {
				for (Session adminSession : adminSessions.values()) {
					if (adminSession.isOpen()) {
						try {
							adminSession.getBasicRemote().sendText(userId + " 님이 상담을 종료했습니다.");
						} catch (IOException e) {
							logger.severe("관리자에게 알림 전송 실패: " + e.getMessage());
						}
					}
				}
			}

			// 세션과 대화 ID 관련 정보 삭제
			sessions.remove(userId);
			adminSessions.remove(userId);
			logger.info("연결 종료: " + userId);
		} else {
			logger.warning("연결 종료 시 사용자 ID를 찾을 수 없습니다.");
		}
	}
}
