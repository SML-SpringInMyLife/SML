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

import org.json.JSONArray;
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

	protected static final Map<String, UserSessionInfo> userSessions = new ConcurrentHashMap<>();
	private static final Map<String, StringBuilder> chatBuffers = new ConcurrentHashMap<>();
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
				handleNewConnection(session, member);
			} else {
				logger.warning("회원 정보가 없습니다.");
			}
		} else {
			logger.warning("HTTP 세션이 없습니다.");
		}
	}

	private void handleNewConnection(Session session, MemberVO member) {
		String userId = member.getMemId();
		int adminCk = member.getMemAdminCheck();

		if (adminCk == 0) {
			String chatSessionId = session.getId();
			String conversationId = generateConversationId(userId, chatSessionId);
			UserSessionInfo sessionInfo = new UserSessionInfo(userId, session, member.getMemName(), member.getMemCode(), conversationId);
			userSessions.put(userId, sessionInfo);
			chatBuffers.putIfAbsent(conversationId, new StringBuilder());
			logger.info("연결된 회원: " + userId);
		}

		try {
			if (adminCk == 1) {
				adminSessions.put(userId, session);
				logger.info("관리자 로그인: " + userId);
				// 활성화된 채팅방 목록을 관리자에게 전송
				sendActiveChatRooms(session);
			} else {
				session.getBasicRemote().sendText("안녕하세요, " + userId + "님!");
				session.getBasicRemote().sendText("담당자가 입장 중입니다. 잠시만 기다려 주세요.");
			}
		} catch (IOException e) {
			logger.severe("메시지 전송 실패: " + e.getMessage());
		}
	}

	private void sendActiveChatRooms(Session session) throws IOException {
		JSONObject chatRoomList = new JSONObject();
		chatRoomList.put("type", "chatRooms");
		chatRoomList.put("chatRooms", getActiveChatRooms());
		// session.getBasicRemote().sendText(chatRoomList.toString());
	}

	private JSONArray getActiveChatRooms() {
		JSONArray chatRooms = new JSONArray();
		for (Map.Entry<String, UserSessionInfo> entry : userSessions.entrySet()) {
			JSONObject room = new JSONObject();
			room.put("id", entry.getValue().getConversationId());
			room.put("name", entry.getValue().getMemName());
			chatRooms.put(room);
		}
		logger.info("활성화된 채팅방 목록: " + chatRooms.toString());
		return chatRooms;
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
		MemberVO member = (MemberVO) httpSession.getAttribute("member");

		try {
			if (message.trim().startsWith("{")) {
				JSONObject jsonMessage = new JSONObject(message);
				logger.info("수신된 JSON 메시지: " + jsonMessage.toString());

				String action = jsonMessage.optString("action");

				if ("getChatRooms".equals(action)) {
					sendActiveChatRooms(session);
					return;
				}

				if ("joinChatRoom".equals(action)) {
					String conversationId = jsonMessage.optString("conversationId");
					joinChatRoom(conversationId, session);
					return;
				}

				processChatMessage(jsonMessage, member);
			} else {
				logger.warning("수신된 메시지가 JSON 형식 아님: " + message);
			}
		} catch (JSONException e) {
			logger.severe("메시지 처리 중 JSON 오류 발생: " + message);
		} catch (IOException e) {
			logger.severe("클라이언트로 메시지 전송 중 오류 발생: " + e.getMessage());
		}
	}

	private void joinChatRoom(String conversationId, Session session) throws IOException {
		// 대화 ID로 세션 정보 가져오기
		UserSessionInfo sessionInfo = userSessions.get(conversationId);
		logger.info("sessionInfo: " + sessionInfo);

		if (sessionInfo != null) {
			// 현재 사용자의 세션 업데이트
			sessionInfo.setSession(session);
			session.getBasicRemote().sendText("채팅방에 입장했습니다: " + conversationId);
			logger.info("채팅방에 입장: " + conversationId);

			// 선택적으로, 채팅방의 현재 채팅 기록을 사용자에게 전송
			StringBuilder buffer = chatBuffers.get(conversationId);
			if (buffer != null) {
				session.getBasicRemote().sendText(buffer.toString());
			} else {
				logger.warning("해당 대화 ID의 채팅 버퍼를 찾을 수 없습니다.");
			}
		} else {
			session.getBasicRemote().sendText("채팅방을 찾을 수 없습니다: " + conversationId);
			logger.warning("채팅방을 찾을 수 없습니다: " + conversationId);
		}
	}

	private void processChatMessage(JSONObject jsonMessage, MemberVO member) throws IOException {
		String userId = member.getMemId();
		String memName = member.getMemName();
		String content = jsonMessage.getString("content");
		String conversationId = getConversationId(userId);

		if (conversationId == null) {
			logger.warning("대화 ID가 null입니다. 메시지 저장 실패.");
			return;
		}

		StringBuilder buffer = chatBuffers.get(conversationId);
		if (buffer != null) {
			String formattedTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
			buffer.append(memName + "(" + userId + ") : " + content + " (" + formattedTime + ") // \n  ");
		} else {
			logger.warning("해당 대화 ID의 채팅 버퍼를 찾을 수 없습니다.");
		}

		sendMessageToAllSessions(conversationId, userId, memName, content);
	}

	private void sendMessageToAllSessions(String conversationId, String userId, String memName, String content)
			throws IOException {
		JSONObject responseMessage = new JSONObject();
		responseMessage.put("userId", userId);
		responseMessage.put("memName", memName);
		responseMessage.put("content", content);
		responseMessage.put("timestamp", System.currentTimeMillis());

		for (UserSessionInfo info : userSessions.values()) {
			if (info.getConversationId().equals(conversationId)) {
				Session s = info.getSession();
				if (s.isOpen()) {
					s.getBasicRemote().sendText(responseMessage.toString());
				}
			}
		}
	}

	@OnClose
	public void onClose(Session session) {
		String userId = null;
		MemberVO member = null;
		String conversationId = null;

		for (Map.Entry<String, UserSessionInfo> entry : userSessions.entrySet()) {
			if (entry.getValue().getSession().equals(session)) {
				userId = entry.getKey();
				HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
				if (httpSession != null) {
					member = (MemberVO) httpSession.getAttribute("member");
				}
				conversationId = entry.getValue().getConversationId();
				break;
			}
		}

		if (userId != null && conversationId != null) {
			handleSessionClosure(userId, member, conversationId);
		} else {
			logger.warning("연결 종료 시 사용자 ID를 찾을 수 없습니다.");
		}
	}

	private void handleSessionClosure(String userId, MemberVO member, String conversationId) {
		StringBuilder buffer = chatBuffers.remove(conversationId);
		if (buffer != null) {
			saveChatContent(conversationId, buffer.toString().trim(), member);
		} else {
			logger.warning("채팅 버퍼가 비어 있습니다. 세션 종료 시 채팅 내용이 없습니다.");
		}

		if (member != null && member.getMemAdminCheck() != 1) {
			notifyAdmins(userId);
		}

		userSessions.remove(userId);
		adminSessions.remove(userId);
		logger.info("연결 종료: " + userId);
	}

	private void saveChatContent(String conversationId, String chatContent, MemberVO member) {
		if (chatContent.isEmpty()) {
			logger.info("채팅 내용이 빈 값입니다. 저장하지 않음.");
		} else {
			ChatVO chat = new ChatVO();
			chat.setConversationId(conversationId);
			chat.setCategoryCode(1); // 기본 카테고리 코드
			chat.setChatContent(chatContent);
			chat.setMemCode(member.getMemCode());
			chat.setChatDate(new Date());
			chat.setStatus(1); // 기본 상태

			try {
				service.saveChatContent(chat);
				logger.info("채팅 내용 저장됨: " + chat);
			} catch (Exception e) {
				logger.severe("채팅 내용 저장 실패: " + e.getMessage());
			}
		}
	}

	private void notifyAdmins(String userId) {
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

	private String generateConversationId(String userId, String chatSessionId) {
		return userId + "_" + chatSessionId;
	}

	private String getConversationId(String userId) {
		UserSessionInfo info = userSessions.get(userId);
		return (info != null) ? info.getConversationId() : null;
	}
}