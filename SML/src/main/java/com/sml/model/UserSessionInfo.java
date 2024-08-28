package com.sml.model;

import javax.websocket.Session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessionInfo {
    private String userId;
    private Session session;
    private String memName;
    private int memCode;
    private String chatSessionId; // 대화 세션 ID 추가

    public UserSessionInfo(String userId, Session session, String memName, int memCode, String chatSessionId) {
        this.userId = userId;
        this.session = session;
        this.memName = memName;
        this.memCode = memCode;
        this.chatSessionId = chatSessionId;
    }
}
