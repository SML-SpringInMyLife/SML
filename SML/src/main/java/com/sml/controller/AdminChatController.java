package com.sml.controller;

import java.io.IOException;
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

import com.sml.model.ChatVO;
import com.sml.model.MemberVO;
import com.sml.service.AdminService;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class AdminChatController {

	private static final Logger logger = Logger.getLogger(AdminChatController.class.getName());

	// 사용자 ID와 WebSocket 세션을 매핑하기 위한 Map
	private static final Map<String, Session> sessions = new ConcurrentHashMap<>();
	private static final Map<Session, StringBuilder> chatBuffers = new ConcurrentHashMap<>(); // 채팅 메시지를 누적할 버퍼
	private static final Map<String, Session> adminSessions = new ConcurrentHashMap<>();
	@Autowired
	private AdminService service; // AdminService 인스턴스

	@OnOpen
	public void onOpen(Session session) {
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");

		if (httpSession != null) {
			MemberVO member = (MemberVO) httpSession.getAttribute("member");
			if (member != null) {
				String userId = member.getMemId();
				int memAdminCheck = member.getMemAdminCheck();
				sessions.put(userId, session); // 사용자의 ID와 세션을 Map에 저장
				chatBuffers.put(session, new StringBuilder()); // 세션별 채팅 버퍼 초기화
				logger.info("연결된 회원: " + userId + " / memAdminCheck : " + memAdminCheck);

				if (memAdminCheck == 1) {
					adminSessions.put(userId, session); // 관리자의 세션을 저장
					logger.info("관리자 로그인: " + userId);

				} else {
					try {
						// 클라이언트에게 환영 메시지 전송
						logger.info("환영메시지 발송 : " + userId);
						session.getBasicRemote().sendText("안녕하세요, " + userId + " 님!");
						session.getBasicRemote().sendText("무엇을 도와드릴까요?");

						// 관리자에게 알림 전송
						for (Session adminSession : adminSessions.values()) {
							adminSession.getBasicRemote().sendText(userId + " 님이 접속하였습니다.");
						}
					} catch (IOException e) {
						logger.severe("환영메시지 발송 실패 : " + e.getMessage());
						e.printStackTrace();
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

				String userId = member.getMemId(); // 발신자 ID
				String content = jsonMessage.getString("content");

				// 세션의 채팅 버퍼에 메시지 추가
				StringBuilder buffer = chatBuffers.get(session);
				if (buffer != null) {
					buffer.append(content).append("\n"); // 메시지와 새 줄 추가
				}

				JSONObject responseMessage = new JSONObject();
				responseMessage.put("userId", userId);
				responseMessage.put("content", content);
				responseMessage.put("timestamp", System.currentTimeMillis());

				// 모든 연결된 세션에 메시지 브로드캐스트
				for (Session s : sessions.values()) {
					if (s.isOpen()) {
						s.getBasicRemote().sendText(responseMessage.toString());
					}
				}
			} else {
				logger.warning("수신된 메시지가 JSON 형식 아님 : " + message);
			}
		} catch (JSONException e) {
			logger.severe("메시지 처리 중 JSON 오류 발생: " + message);
			e.printStackTrace();
		} catch (IOException e) {
			logger.severe("클라이언트로 메시지 전송 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		// 해당 세션과 연결된 사용자 ID를 찾아서 제거
		String userId = null;
		MemberVO member = null;

		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			if (entry.getValue().equals(session)) {
				userId = entry.getKey();
				HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
				if (httpSession != null) {
					member = (MemberVO) httpSession.getAttribute("member");
				}
				break;
			}
		}

		if (userId != null) {
			sessions.remove(userId);
			logger.info("연결 종료: " + userId);

			// 세션 종료 시 채팅 내용 저장
			StringBuilder buffer = chatBuffers.remove(session);
			if (buffer != null) {
				String chatContent = buffer.toString();
				logger.info("chatContent : =============================> " + chatContent);

				// ChatVO 객체 생성 및 설정
				ChatVO chatVo = new ChatVO();

				// 카테고리 코드와 상태는 예시 값, 실제 값으로 교체해야 함
				int categoryCode = 1; // 실제 카테고리 코드로 교체
				int status = 1; // 채팅 상태 예시 값, 필요에 따라 조정
				chatVo.setCategoryCode(categoryCode);
				chatVo.setChatContent(chatContent);
				chatVo.setStatus(status);

				// `memAdminCheck` 값 확인 및 저장
				if (member != null) {
					int memCode = member.getMemCode(); // 실제 멤버 코드로 설정
					int memAdminCheck = member.getMemAdminCheck();

					if (memAdminCheck != 1) {
						chatVo.setMemCode(memCode);

						// 데이터베이스에 채팅 내용 저장
						try {
							if (service != null) {
								service.saveChatContent(chatVo);
								logger.info("채팅 내용 저장됨: " + chatContent);
							} else {
								logger.severe("AdminService 인스턴스가 null입니다.");
							}
						} catch (Exception e) {
							logger.severe("채팅 내용 저장 실패: " + e.getMessage());
							e.printStackTrace();
						}
					} else {
						logger.info("관리자 채팅 내용은 저장되지 않습니다.");
					}
				} else {
					logger.warning("회원 정보가 존재하지 않아 채팅 내용을 저장할 수 없습니다.");
				}
			} else {
				logger.warning("채팅 버퍼가 null입니다. 세션 종료 시 채팅 내용이 없습니다.");
			}

			// 관리자에게 알림 전송
			if (member != null && member.getMemAdminCheck() != 1) {
				for (Session adminSession : adminSessions.values()) {
					if (adminSession.isOpen()) { // 세션이 열린 상태에서만 메시지 전송
						try {
							adminSession.getBasicRemote().sendText(userId + " 님이 상담을 종료했습니다.");
						} catch (IOException e) {
							logger.severe("관리자에게 알림 전송 실패: " + e.getMessage());
						}
					}
				}
			}
		} else {
			logger.warning("연결 종료 시 사용자 ID를 찾을 수 없습니다.");
		}
	}
}
