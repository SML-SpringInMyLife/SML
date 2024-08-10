package com.sml.service;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.json.JSONObject;

/**
 * WebSocket 클라이언트 예제 WebSocket 서버와 연결하여 메시지를 주고받는 클라이언트 프로그램
 */
public class WebSocketClientExample extends Endpoint {

	// WebSocket 세션을 저장하는 변수
	private Session userSession = null;

	/**
	 * WebSocket 클라이언트 실행 메인 메서드
	 */
	public static void main(String[] args) {
		// WebSocket 컨테이너 생성
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();

		// WebSocket 서버 URI
		String uri = "ws://192.68.1.114:8080/chat";

		try {
			// 서버에 연결
			container.connectToServer(new WebSocketClientExample(), URI.create(uri));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * WebSocket 서버와 연결이 열릴 때 호출되는 메서드
	 * 
	 * @param userSession WebSocket 세션
	 * @param config      엔드포인트 설정
	 */
	@Override
	public void onOpen(Session userSession, EndpointConfig config) {
		// 연결된 세션 저장
		this.userSession = userSession;
		System.out.println("서버에 연결되었습니다.");

		// 서버에 JSON 형식의 텍스트 메시지 전송
		sendMessage("greeting", "클라이언트에서 안녕하세요!");

		// 메시지 핸들러 추가
		userSession.addMessageHandler(new MessageHandler.Whole<String>() {
			/**
			 * 서버에서 메시지를 수신할 때 호출되는 메서드
			 * 
			 * @param message 수신된 메시지
			 */
			@Override
			public void onMessage(String message) {
				System.out.println("수신된 메시지: " + message);
			}
		});
	}

	/**
	 * 서버에 JSON 메시지를 전송하는 메서드
	 * 
	 * @param type    메시지 타입
	 * @param content 메시지 내용
	 */
	public void sendMessage(String type, String content) {
		if (userSession != null && userSession.isOpen()) {
			try {
				// JSON 객체 생성
				JSONObject jsonMessage = new JSONObject();
				jsonMessage.put("type", type);
				jsonMessage.put("content", content);
				// 서버에 메시지 전송
				userSession.getBasicRemote().sendText(jsonMessage.toString());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("서버에 메시지 전송 실패");
			}
		}
	}
}
