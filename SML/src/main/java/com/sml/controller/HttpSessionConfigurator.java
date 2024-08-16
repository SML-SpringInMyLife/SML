package com.sml.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
	private static final Logger logger = LoggerFactory.getLogger(HttpSessionConfigurator.class);

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();

		if (httpSession == null) {
			httpSession = ((HttpServletRequest) request).getSession(true); // 세션이 없으면 생성
		}

		if (httpSession != null) {
			sec.getUserProperties().put("httpSession", httpSession);
		} else {
			logger.error("WebSocket 업그레이드 시 HttpSession을 가져올 수 없습니다.");
		}
	}
}