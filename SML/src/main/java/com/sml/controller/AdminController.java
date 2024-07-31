package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception {

		logger.info("관리자 페이지 이동");

	}
	
	@RequestMapping(value = "courses", method = RequestMethod.GET)
	public void adminCoursesGET() throws Exception {

		logger.info("관리자 - 수강신청관리 페이지 이동");

	}
	
	@RequestMapping(value = "members", method = RequestMethod.GET)
	public void adminMembersGET() throws Exception {

		logger.info("관리자 - 회원관리페이지 이동");

	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void adminEditGET() throws Exception {

		logger.info("관리자 - 정보수정페이지 이동");

	}
	
	@RequestMapping(value = "sms", method = RequestMethod.GET)
	public void adminSmsGET() throws Exception {

		logger.info("관리자 - 문자관리페이지 이동");

	}
	
	@RequestMapping(value = "chat", method = RequestMethod.GET)
	public void adminChatGET() throws Exception {

		logger.info("관리자 - 채팅상담관리페이지 이동");

	}
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void adminLoginGET() throws Exception {
		
		logger.info("로그인페이지 이동");
		
	}
}
