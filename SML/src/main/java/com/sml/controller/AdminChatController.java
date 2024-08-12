package com.sml.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.sml.model.ChatVO;
import com.sml.model.MemberVO;
import com.sml.service.AdminService;

@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class AdminChatController {

	private static final Logger logger = Logger.getLogger(AdminChatController.class.getName());

	// 사용자 ID와 WebSocket 세션을 매핑하기 위한 Map
	private static final Map<String, Session> sessions = new ConcurrentHashMap<>();
	private static final Map<Session, StringBuilder> chatBuffers = new ConcurrentHashMap<>(); // 채팅 메시지를 누적할 버퍼
	private AdminService service; // ChatService 인스턴스

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

				if (memAdminCheck != 1) {
					try {
						// 클라이언트에게 환영 메시지 전송
						logger.info("환영메시지 발송 : " + userId);
						session.getBasicRemote().sendText("안녕하세요, " + userId + " 님!");
						session.getBasicRemote().sendText("무엇을 도와드릴까요?");
					} catch (IOException e) {
						logger.severe("환영메시지 발송 실패 : " + e.getMessage());
						e.printStackTrace();
					}
				} else {
					logger.info("관리자는 환영메시지 없음");
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
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			if (entry.getValue().equals(session)) {
				userId = entry.getKey();
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

				// 카테고리 코드와 멤버 코드 예시 값, 실제 값으로 교체해야 함
				int categoryCode = 1; // 실제 카테고리 코드로 교체
				int memCode = 1; // 실제 멤버 코드로 교체하는 메서드 호출
				int status = 1; // 채팅 상태 예시 값, 필요에 따라 조정

				// ChatVO 객체 생성
				ChatVO chatVo = new ChatVO();
				chatVo.setCategoryCode(categoryCode);
				chatVo.setMemCode(memCode);
				chatVo.setChatContent(chatContent);
				chatVo.setStatus(status);

				// 데이터베이스에 채팅 내용 저장
				try {
					service.saveChatContent(chatVo);
					logger.info("채팅 내용 저장됨: " + chatContent);
				} catch (Exception e) {
					logger.severe("채팅 내용 저장 실패: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	@PreDestroy
	public void cleanup() {
		logger.info("종료 전 리소스 정리");
		sessions.clear(); // 애플리케이션 종료 시 모든 세션을 정리
		chatBuffers.clear(); // 채팅 버퍼 정리
	}
}
