package com.sml.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.model.MemberVO;
import com.sml.service.AdminService;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	/*
	 * @Autowired private AdminService service;
	 */

	/* 관리자 메인 페이지 이동 */
	@GetMapping(value = "main")
	public void adminMainGET() throws Exception {

		logger.info("관리자 페이지 이동");

	}

	@GetMapping(value = "courses")
	public void adminCoursesGET() throws Exception {

		logger.info("관리자 - 수강신청관리 페이지 이동");

	}

	@GetMapping(value = "members")
	public void adminMembersGET() throws Exception {

		logger.info("관리자 - 회원관리페이지 이동");

		/*
		 * List<MemberVO> list = service.getMemberList(); if (!list.isEmpty()) {
		 * model.addAttribute("list", list); } else { model.addAttribute("listCheck",
		 * "empty"); }
		 */

	}

	@GetMapping(value = "edit")
	public void adminEditGET() throws Exception {

		logger.info("관리자 - 정보수정페이지 이동");

	}

	@GetMapping(value = "sms")
	public void adminSmsGET() throws Exception {

		logger.info("관리자 - 문자관리페이지 이동");

	}

	@PostMapping(value = "sms/sendSms.do")
	public String sendSms(HttpServletRequest request) throws Exception {

		// API Key와 Secret Key를 입력 (Coolsms에서 발급받은 값)
		String api_key = "";
		String api_secret = "";
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

	@GetMapping(value = "chat")
	public void adminChatGET() throws Exception {

		logger.info("관리자 - 채팅상담관리페이지 이동");

	}

	@GetMapping(value = "login")
	public void adminLoginGET() throws Exception {

		logger.info("로그인페이지 이동");

	}
}