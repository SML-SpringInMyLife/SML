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

import com.sml.model.CommunityVO;
import com.sml.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private CommunityService service;
	
//	@RequestMapping(value = "boardList", method = RequestMethod.GET)
//	public void adminMainGET() throws Exception{
//		logger.info("커뮤니티 페이지 이동");
//	}
	
	@GetMapping("/boardList")
	public String boardListGET(Model model) throws Exception {
		
		logger.info("커뮤니티 페이지 이동");
		
		List list = service.getBoardList();

		if(!list.isEmpty()) {
			model.addAttribute("list", list);
    	} else {
    		logger.info("fail");
    		model.addAttribute("listCheck", "empty");
    	}
		
		return "community/boardList";

	}
	
	@GetMapping("/enroll")
	public void communityEnrollGET() throws Exception{
		logger.info("글 등록 페이지 접속");
	}
	@PostMapping("/communityEnroll.do")
	public String communityEnrollPOST(CommunityVO community, RedirectAttributes rttr) throws Exception {
		logger.info("enroll : " +community);
		service.communityEnroll(community);
		rttr.addFlashAttribute("enroll_result", community.getCommTitle());
		return "redirect:/community/boardList";
	}
	
}
