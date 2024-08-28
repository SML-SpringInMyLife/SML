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

	// 사용자 ID와 WebSocket 세션을 매핑하기 위한 Map
	private static final Map<String, UserSessionInfo> sessions = new ConcurrentHashMap<>();
	private static final Map<Session, StringBuilder> chatBuffers = new ConcurrentHashMap<>(); // 채팅 메시지를 누적할 버퍼
	private static final Map<String, Session> adminSessions = new ConcurrentHashMap<>();

	private static AdminService service;

	@Autowired
	public void setAdminService(AdminService adminService) {
		service = adminService;
	}

	@OnOpen
	public void onOpen(Session session) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); // Spring 컨텍스트에서 주입 처리
		// WebSocket 세션에서 HTTP 세션 정보를 가져옴
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");

		if (httpSession != null) {
			// HTTP 세션에서 회원 정보를 가져옴
			MemberVO member = (MemberVO) httpSession.getAttribute("member");
			if (member != null) {
				// 회원의 ID, 코드, 관리자 여부를 가져옴
				String userId = member.getMemId();
				int memCode = member.getMemCode();
				String memName = member.getMemName();
				int memAdminCheck = member.getMemAdminCheck();
				String chatSessionId = session.getId(); // 세션 ID 사용
				logger.info("세션 아이디 =================: " + chatSessionId);

				// UserSessionInfo 객체를 만들어 sessions 맵에 저장 (ID, 세션, 회원 이름, 회원 코드)
				sessions.put(userId, new UserSessionInfo(userId, session, memName, memCode, chatSessionId));

				// 세션별로 채팅 버퍼를 초기화
				chatBuffers.put(session, new StringBuilder());
				logger.info("연결된 회원: " + userId + " / memAdminCheck : " + memAdminCheck);

				// 관리자인 경우 관리자 세션에 추가
				if (memAdminCheck == 1) {
					adminSessions.put(userId, session);
					logger.info("관리자 로그인: " + userId);
				} else {
					try {
						// 일반 회원에게 환영 메시지 전송
						logger.info("환영메시지 발송 : " + userId);
						session.getBasicRemote().sendText("안녕하세요, " + userId + " 님!");
						session.getBasicRemote().sendText("무엇을 도와드릴까요?");

						// 관리자가 있으면 접속 알림 전송
						for (Session adminSession : adminSessions.values()) {
							adminSession.getBasicRemote().sendText(userId + " 님이 접속하였습니다.");
						}
					} catch (IOException e) {
						logger.severe("환영메시지 발송 실패 : " + e.getMessage());
						e.printStackTrace();
					}
				}
			} else {
				// 회원 정보가 없는 경우 경고 로그 출력
				logger.warning("회원 정보가 존재하지 않습니다.");
			}
		} else {
			// HTTP 세션이 없는 경우 경고 로그 출력
			logger.warning("HTTP 세션이 null입니다.");
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		// 메시지 수신 시 처리
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
		MemberVO member = (MemberVO) httpSession.getAttribute("member");

		try {
			if (message.trim().startsWith("{")) {
				// JSON 메시지를 처리
				JSONObject jsonMessage = new JSONObject(message);
				logger.info("수신된 JSON 메시지: " + jsonMessage.toString());

				String userId = member.getMemId(); // 발신자 ID
				String memName = member.getMemName(); // 발신자 이름
				String content = jsonMessage.getString("content");

				// 세션의 채팅 버퍼에 메시지 추가
				StringBuilder buffer = chatBuffers.get(session);
				if (buffer != null) {
					Date currentTime = new Date();
					String formattedTime = new SimpleDateFormat("HH:mm:ss").format(currentTime);
					buffer.append(memName + " : " + content + " (" + formattedTime + ") // \n  "); // 메시지마다 개행하고 싶은데, \n이 안먹음....일단 // 추
				}

				// 모든 연결된 세션에 메시지 전송
				JSONObject responseMessage = new JSONObject();
				responseMessage.put("userId", userId);
				responseMessage.put("memName", memName);
				responseMessage.put("content", content);
				responseMessage.put("timestamp", System.currentTimeMillis());

				for (UserSessionInfo sessionInfo : sessions.values()) {
					Session s = sessionInfo.getSession();
					if (s.isOpen()) {
						s.getBasicRemote().sendText(responseMessage.toString());
					}
				}
			} else {
				// JSON 형식이 아닌 메시지를 경고
				logger.warning("수신된 메시지가 JSON 형식 아님 : " + message);
			}
		} catch (JSONException e) {
			// JSON 처리 오류
			logger.severe("메시지 처리 중 JSON 오류 발생: " + message);
			e.printStackTrace();
		} catch (IOException e) {
			// 메시지 전송 중 오류
			logger.severe("클라이언트로 메시지 전송 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		// 연결이 종료될 때 사용자와 세션 정보를 찾음
		String userId = null;
		MemberVO member = null;

		// sessions 맵에서 해당 세션과 매칭되는 사용자 정보를 검색
		for (Map.Entry<String, UserSessionInfo> entry : sessions.entrySet()) {
			if (entry.getValue().getSession().equals(session)) {
				userId = entry.getKey(); // 사용자 ID를 찾음
				HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
				if (httpSession != null) {
					member = (MemberVO) httpSession.getAttribute("member"); // HTTP 세션에서 회원 정보를 가져옴
				}
				break;
			}
		}

		if (userId != null) {
			// 세션 종료 시 채팅 내용을 가져옴
			StringBuilder buffer = chatBuffers.remove(session);
			if (buffer != null) {
				// ChatVO 객체 생성 및 값 설정
				ChatVO chat = new ChatVO();
				// 카테고리 코드는 고정 값 또는 비즈니스 로직에 따라 동적으로 설정
				int categoryCode = 1;
				chat.setCategoryCode(categoryCode); // 카테고리 코드 (예시 값)
				String chatContent = buffer.toString(); // 채팅 내용 (버퍼에서 가져옴)
				// 채팅 내용을 ChatVO에 추가
				chat.setChatContent(chatContent);
				// 세션에서 가져온 멤버 코드 설정
				int memCode = member.getMemCode();
				chat.setMemCode(memCode); // 회원 코드 설정
				Date chatDate = new Date();
				chat.setChatDate(chatDate);
				int status = 1;
				chat.setStatus(status);
				logger.info(" chatVO :------------------>  " + chat.toString());
				try {
					service.saveChatContent(chat);
					logger.info("채팅 내용 저장됨: " + chat.toString());
				} catch (Exception e) {
					logger.severe("service가 유효하지 않음, 채팅 내용 저장 실패: " + e.getMessage());
					e.printStackTrace();
				}
			} else {
				logger.warning("채팅 버퍼가 비어 있습니다. 세션 종료 시 채팅 내용이 없습니다.");
			}

			// 관리자가 있는 경우 관리자에게 사용자 종료 알림
			if (member != null && member.getMemAdminCheck() != 1) {
				for (Session adminSession : adminSessions.values()) {
					if (adminSession.isOpen()) {
						try {
							// 관리자에게 종료 알림 메시지 전송
							adminSession.getBasicRemote().sendText(userId + " 님이 상담을 종료했습니다.");
						} catch (IOException e) {
							logger.severe("관리자에게 알림 전송 실패: " + e.getMessage());
						}
					}
				}
			}
		} else {
			// 사용자 ID를 찾지 못한 경우 경고 메시지 출력
			logger.warning("연결 종료 시 사용자 ID를 찾을 수 없습니다.");
		}
		// 사용자가 존재할 경우 sessions에서 제거
		sessions.remove(userId);
		logger.info("연결 종료: " + userId);
	}
}
