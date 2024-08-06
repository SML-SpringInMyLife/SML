package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.service.MypageService;

@Controller
@RequestMapping("/member")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	//@Autowired
	//private MypageService service;
	
	
	/* 회원 마이페이지 이동 */
	@GetMapping("mypage")
	public void memberMypageGET() throws Exception {

		logger.info("회원 마이페이지 이동");

	}
	
	/* 회원정보 페이지 이동*/
	@GetMapping("myinfo")
	public void memberMyinfoGET() throws Exception {

		logger.info("회원정보 페이지 이동");

	}
	
	/* 회원 수강신청 페이지 이동 */
	@GetMapping("mycourses")
	public void memberCoursesGET() throws Exception {

		logger.info("회원 수강신청 페이지 이동");

	}
	
	/* 회원 적립금 페이지 이동 */
	@GetMapping("point")
	public void memberPointGET() throws Exception {

		logger.info("회원 적립금 페이지 이동");

	}
	
	/* 회원 출석체크 페이지 이동 */
	@GetMapping("dailyCheck")
	public void memberDailyCheckGET() throws Exception {

		logger.info("회원 출석체크 페이지 이동");

	}
	
	

}
