package com.sml.controller;

import java.io.IOException;
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

import com.sml.model.MemberVO;

/**
 * WebSocket 서버 엔드포인트 클라이언트와의 WebSocket 통신을 처리하는 컨트롤러
 */
@ServerEndpoint(value = "/chat", configurator = HttpSessionConfigurator.class)
public class AdminChatController {

	private static final Logger logger = Logger.getLogger(AdminChatController.class.getName());

	/**
	 * 클라이언트와의 연결이 열릴 때 호출되는 메서드
	 * 
	 * @param session WebSocket 세션
	 */
	@OnOpen
	public void onOpen(Session session) {
		// WebSocket 세션에서 HttpSession을 가져옴
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");

		if (httpSession != null) {
			MemberVO member = (MemberVO) httpSession.getAttribute("member");
			if (member != null) {
				logger.info("연결된 회원: " + member.getMemId());
				// 클라이언트로 메시지 전송 (예시), 기본메시지 활용
				try {
					session.getBasicRemote().sendText("안녕하세요, " + member.getMemId() + " 님! 무엇을 도와드릴까요?");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				logger.warning("회원 정보가 존재하지 않습니다.");
			}
		} else {
			logger.warning("HTTP 세션이 null입니다.");
		}
	}

	/**
	 * 클라이언트로부터 메시지를 받을 때 호출되는 메서드
	 * 
	 * @param message 수신된 메시지
	 * @param session WebSocket 세션
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
		MemberVO member = (MemberVO) httpSession.getAttribute("member");

		try {
			if (message.trim().startsWith("{")) {
				JSONObject jsonMessage = new JSONObject(message);
				logger.info("수신된 JSON 메시지: " + jsonMessage.toString());

				String userId = member.getMemId(); // 발신자 ID

				for (Session s : session.getOpenSessions()) {
					if (s.isOpen()) {
						JSONObject responseMessage = new JSONObject();
						responseMessage.put("userId", userId);
						responseMessage.put("content", jsonMessage.getString("content"));

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

	/**
	 * 클라이언트와의 연결이 닫힐 때 호출되는 메서드
	 * 
	 * @param session WebSocket 세션
	 */
	@OnClose
	public void onClose(Session session) {
		logger.info("연결종료 : " + session.getId());
	}

	@PreDestroy
	public void cleanup() {
		logger.info("Cleaning up resources before shutdown");
	}
}