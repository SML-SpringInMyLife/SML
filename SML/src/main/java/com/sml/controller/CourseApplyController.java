package com.sml.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.CourseApplyDTO;
import com.sml.model.MemberVO;
import com.sml.service.CourseApplyService;

@Controller
public class CourseApplyController {

	private static final Logger logger = LoggerFactory.getLogger(CourseApplyController.class);

	@Autowired
	private CourseApplyService service;
	
	@PostMapping("/course/apply")
	@ResponseBody
	public String applyPOST(CourseApplyDTO apply, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("member");
		if(mvo == null) {
			return "5";
		}
		
		int result = service.enrollApply(apply);
		service.coursePoint(mvo);
		return result +"";
	}
	
}