package com.sml.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.CourseApplyDTO;
import com.sml.model.CourseVO;
import com.sml.model.MemberVO;
import com.sml.service.CourseApplyService;

@Controller
public class CourseApplyController {

	private static final Logger logger = LoggerFactory.getLogger(CourseApplyController.class);

	@Autowired
	private CourseApplyService service;
	
	@PostMapping("/course/apply")
	public String applyPOST(@RequestParam("courseCode") int courseCode, CourseApplyDTO apply, RedirectAttributes rttr, HttpSession session) throws Exception {
		MemberVO member = (MemberVO)session.getAttribute("member");
		if (member == null) {
	        rttr.addFlashAttribute("error_message", "로그인해 주세요.");
	        return "redirect:/course/boardList";
	    }
		
		CourseVO course = (CourseVO)session.getAttribute("course");
		if (course == null) {
	        rttr.addFlashAttribute("error_message", "Course session is not available.");
	        return "redirect:/community/boardList";
	    }
		
		int memCode = (Integer)session.getAttribute("memCode");
		
		apply.setMemCode(memCode);
		apply.setCourseCode(courseCode);
		
		service.courseApply(apply);
		service.coursePoint(member);
		rttr.addFlashAttribute("apply_result", apply.getCourseName());
		return "/member/mycourses";
	}
	
}