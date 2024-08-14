package com.sml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.CourseApplyVO;
import com.sml.model.Criteria;
import com.sml.model.PageDTO;
import com.sml.service.CourseApplyService;

@Controller
//@RequestMapping("/course")
public class CourseApplyController {

	private static final Logger logger = LoggerFactory.getLogger(CourseApplyController.class);

	@Autowired
	private CourseApplyService service;
	
	@GetMapping("/member/mycourses")
	public void applyListGET(Criteria cri, Model model) throws Exception {
		List list = service.applyList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, service.applyTotal(cri)));
	}
	
	// == enroll
	@PostMapping("/apply")
	public String applyPOST(CourseApplyVO apply, RedirectAttributes rttr) throws Exception {
		service.applyApply(apply);
		rttr.addFlashAttribute("apply_result", apply.getCourseName());
		return "redirect:/member/mycourses";
	}
	
	// cancel
	@PostMapping("/member/mycourses")
	public String cancelPOST(int applyCode, RedirectAttributes rttr) throws Exception {
//		int result = service.
//		rttr.addFlashAttribute("cancel_result", result);
		return "redirect:/member/mycourses";
	}


}