package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private CourseService service;
	
	@GetMapping("/boardList")
	public void booardListGET() throws Exception{
		logger.info("수강신청 페이지 이동");
	}

}
