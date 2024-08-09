package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService service;

	@GetMapping("/boardList")
	public void booardListGET() throws Exception {
		logger.info("수강신청 페이지 진입");
	}

	@GetMapping("/enroll")
	public void courseEnrollGET() throws Exception {
		logger.info("수업 등록 페이지 진입");
	}

	@PostMapping("/enroll")
	public String enrollPOST() throws Exception {
		return "redirect:/course/boardList";
	}

	@GetMapping("/modify")
	public void modifyGET() throws Exception {
		logger.info("수업 수정 페이지 진입");
	}

	@PostMapping("/modify")
	public String modifyPOST() throws Exception {
		// 해당 상세 페이지로 리턴할 수 있나..?
		return "redirect:/course/boardList";
	}

	@PostMapping("/delete")
	public String deletePOST() throws Exception {
		return "redirect:/course/boardList";
	}

}