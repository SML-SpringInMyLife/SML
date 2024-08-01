package com.sml.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class AdminMessageController {
	@RequestMapping(value = "/admin/sendSms.do", method = RequestMethod.POST)
	public String sendSms(HttpServletRequest request) throws Exception {

		// API Key와 Secret Key를 입력 (Coolsms에서 발급받은 값)
		String api_key = "NCS63DEMZCYPEZWK";
		String api_secret = "AFLTYEJF4IAJ7YMON1HRWMGNQO2I5HGK";
		Message coolsms = new Message(api_key, api_secret);

		// SMS 전송을 위한 파라미터 설정
		HashMap<String, String> set = new HashMap<>();
		set.put("to", request.getParameter("recipientNumber")); // 수신번호
		set.put("from", request.getParameter("senderNumber")); // 발신번호
		set.put("text", request.getParameter("smsContent")); // 문자 내용
		set.put("type", "sms"); // 문자 타입
		set.put("app_version", "test app 1.2"); // 애플리케이션 버전

		System.out.println(set);

		try {
			// SMS 전송 및 결과 받기
			JSONObject result = coolsms.send(set);
			System.out.println(result.toString());
		} catch (CoolsmsException e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Code: " + e.getCode());
		}

		// SMS 전송 후 응답 페이지로 이동
		return "admin/sms"; // SMS 전송 결과 페이지
	}
}