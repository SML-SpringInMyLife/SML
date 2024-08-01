package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	/* 공지사항 메인페이지(목록페이지) 이동 */
	@GetMapping("/list")
	public void noticeMainGET() throws Exception {

		logger.info("공지사항 페이지 이동");

	}
	
	/* 공지사항 등록페이지 이동 */
	@GetMapping("/enroll")
	public void noticeEnrollGET() throws Exception {

		logger.info("공지사항 등록페이지 이동");

	}
  
	/* 공지사항 수정페이지 이동 */
	@GetMapping("/modify")
	public void noticeModifyGET() throws Exception {

		logger.info("공지사항 수정페이지 이동");

	}
}
