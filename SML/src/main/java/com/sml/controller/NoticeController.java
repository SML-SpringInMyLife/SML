package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	/* 공지사항 메인페이지(목록페이지) 이동 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void noticeMainGET() throws Exception {

		logger.info("공지사항 페이지 이동");

	}
	
	/* 공지사항 등록페이지 이동 */
	@RequestMapping(value = "enroll", method = RequestMethod.GET)
	public void noticeEnrollGET() throws Exception {

		logger.info("공지사항 등록페이지 이동");

	}
  
	/* 공지사항 수정페이지 이동 */
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void noticeModifyGET() throws Exception {

		logger.info("공지사항 수정페이지 이동");

	}
}
