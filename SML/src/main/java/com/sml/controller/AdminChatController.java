package com.sml.controller;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * WebSocket 서버 엔드포인트
 * 클라이언트와의 WebSocket 통신을 처리하는 컨트롤러
 */
@ServerEndpoint("/chat")
public class AdminChatController {

    /**
     * 클라이언트와의 연결이 열릴 때 호출되는 메서드
     * 
     * @param session WebSocket 세션
     */
    @OnOpen
    public void onOpen(Session session) {
        // 새로운 연결이 열릴 때 호출
        System.out.println("새 연결: " + session.getId());
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
                System.out.println("수신된 JSON 메시지: " + jsonMessage.toString());
            } else {
                // JSON 형식이 아닌 메시지 처리
                System.out.println("수신된 메시지가 JSON 형식이 아닙니다: " + message);
            }
        } catch (JSONException e) {
            // JSON 처리 중 오류 발생 시
            e.printStackTrace();
            System.out.println("메시지 처리 중 JSON 오류 발생: " + message);
        }
    }
}
