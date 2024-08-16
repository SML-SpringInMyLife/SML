package com.sml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.CourseTeacherVO;
import com.sml.model.Criteria;
import com.sml.model.PageDTO;
import com.sml.service.CourseTeacherService;

@Controller
@RequestMapping("/admin")
public class CourseTeacherController {
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private CourseTeacherService service;
	
	@GetMapping("/teacher/enroll")
	public void teacherEnrollGET() throws Exception {
		logger.info("관리자 - 강사 등록 페이지 이동");
	}
	@PostMapping("/teacher/enroll.do")
	public String teacherEnrollPOST(CourseTeacherVO teacher, RedirectAttributes rttr) throws Exception {
		logger.info("강사 등록 : " +teacher);
		service.teacherEnroll(teacher);
		rttr.addFlashAttribute("enroll_result", teacher.getTeaName());
		return "redirect:/admin/teacher/list";
	}
	@GetMapping("/teacher/popup")
	public void teacherPopGET(Criteria cri, Model model) throws Exception {
		logger.info("강사 팝업");
		
		cri.setAmount(5);
		
		List list = service.teacherList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, service.teacherListTotal(cri)));
	}
	
	@GetMapping("/teacher/list")
	public void teacherListGET(Criteria cri, Model model) throws Exception {
		logger.info("강사 관리 페이지 접속");
		List list = service.teacherList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list",list);	
		} else {
			model.addAttribute("listCheck", "empty");	
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, service.teacherListTotal(cri)));
	}
	
	@GetMapping({"/teacher/detail", "/teacher/modify"})
	public void teacherDetailGET(int teaCode, Criteria cri, Model model) throws Exception {
		logger.info("teacherDetail......" +teaCode);
		model.addAttribute("cri", cri);
		model.addAttribute("detail", service.teacherDetail(teaCode));
	}
	
	@PostMapping("/teacher/modify")
	public String teacherModifyPOST(CourseTeacherVO teacher, RedirectAttributes rttr) throws Exception {
		logger.info("teacherModifyPOST...." +teacher);
		int result = service.teacherModify(teacher);
		rttr.addFlashAttribute("modify_result", result);
		return "redirect:/admin/teacher/list";
	}
	
	@PostMapping("/teacher/delete")
	public String teacherDeletePOST(int teaCode, RedirectAttributes rttr) {
		logger.info("deletePOST......................");
		
		int result = 0;
		
		try {
			result = service.teacherDelete(teaCode);
		} catch (Exception e) {
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			
			return "redirect:/admin/teacher/list";	
		}

		rttr.addFlashAttribute("delete_result", result);
		
		return "redirect:/admin/teacher/list";
	}

}
