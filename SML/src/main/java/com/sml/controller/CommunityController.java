package com.sml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void boardListGET(Model model) throws Exception {
		
		logger.info("커뮤니티 페이지 이동");
		
		List list = service.getBoardList();
		
		if(!list.isEmpty()) {
    		model.addAttribute("list", list);
    	} else {
    		model.addAttribute("listCheck", "empty");
    	}
		
		
	}
	
}