package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/safty")
public class SaftyController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	/* 생활안전 조회페이지 이동 */
	@GetMapping("/list")
	public void noticeListGET() throws Exception {

		logger.info("생활안전 조회페이지 이동");

	}
	
	 
}
