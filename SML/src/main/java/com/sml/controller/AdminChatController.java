package com.sml.controller;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * WebSocket 서버 엔드포인트 클라이언트와의 WebSocket 통신을 처리하는 컨트롤러
 */
@ServerEndpoint("/chat")
public class AdminChatController {

	private static final Logger logger = Logger.getLogger(AdminChatController.class.getName());

	/**
	 * 클라이언트와의 연결이 열릴 때 호출되는 메서드
	 * 
	 * @param session WebSocket 세션
	 */
	@OnOpen
	public void onOpen(Session session) {
		logger.info("새 연결 : " + session.getId());
	}

	/**
	 * 클라이언트로부터 메시지를 받을 때 호출되는 메서드
	 * 
	 * @param message 수신된 메시지
	 * @param session WebSocket 세션
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			// 메시지가 JSON 형식인지 확인
			if (message.trim().startsWith("{")) {
				// JSON 객체로 변환
				JSONObject jsonMessage = new JSONObject(message);
				// JSON 메시지 출력
				logger.info("수신된 JSON 메시지: " + jsonMessage.toString());

				// 클라이언트로 메시지 전송
				session.getBasicRemote().sendText("서버 수신 메시지: " + jsonMessage.getString("content"));
			} else {
				// JSON 형식이 아닌 메시지 처리
				logger.warning("수신된 메시지가 JSON 형식 아님 : " + message);
			}
		} catch (JSONException e) {
			// JSON 처리 중 오류 발생 시
			logger.severe("메시지 처리 중 JSON 오류 발생: " + message);
			e.printStackTrace();
		} catch (IOException e) {
			// 클라이언트로 메시지를 전송하는 동안 오류 발생 시
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
}
