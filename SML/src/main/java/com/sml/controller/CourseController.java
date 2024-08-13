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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;
import com.sml.model.PageDTO;
import com.sml.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService service;

	@GetMapping({"/boardList", "/manage"})
	public void booardListGET(Criteria cri, Model model) throws Exception {		
		List list = service.courseList(cri);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, service.courseTotal(cri)));
	}

	@GetMapping("/enroll")
	public void courseEnrollGET(Model model) throws Exception {
		logger.info("수업 등록 페이지 진입");
		ObjectMapper objmapper = new ObjectMapper();
		List list = service.cateList();
		String cateList = objmapper.writeValueAsString(list);
		model.addAttribute("cateList", cateList);
	}
	@PostMapping("/enroll")
	public String enrollPOST(CourseVO course, RedirectAttributes rttr) throws Exception {
		logger.info("enrollPOST......" +course);
		service.courseEnroll(course);
		rttr.addFlashAttribute("enroll_result", course.getCourseName());
		return "redirect:/course/boardList";
	}
	
	@GetMapping("/detail")
	public void detailGET(int courseCode, Criteria cri, Model model) throws Exception {
		logger.info("상세 페이지........." +courseCode);
 
		model.addAttribute("cri", cri);
		model.addAttribute("detail", service.courseDetail(courseCode));
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