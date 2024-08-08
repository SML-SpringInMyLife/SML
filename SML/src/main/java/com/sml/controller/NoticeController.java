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

import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;
import com.sml.service.NoticeService;


@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
   
	@Autowired
	private NoticeService noticeservice;
	
	/* 공지사항 조회페이지 이동 */
	@GetMapping("/list")
	public void noticeListGET(Model model) throws Exception {

		logger.info("공지사항 페이지 이동");
      
		List<NoticeVO> list = noticeservice.noticeGetList();
		
		if(!list.isEmpty()) {
	          model.addAttribute("list", list);
	       } else {
	    	   model.addAttribute("listCheck", "empty");
	       }
	}
	
	/* 공지사항 상제조회페이지 이동 */
	@GetMapping("/detail")
	public void noticeDetailGET() throws Exception {

		logger.info("공지사항 페이지 이동");

	}
	
	/* 공지사항 등록페이지 이동 */
	@GetMapping("/enroll")
	public void noticeEnrollGET() throws Exception {

		logger.info("공지사항 등록페이지 이동");

	}
  
	/* 공지사항 수정페이지 이동 */
	@GetMapping("/modify")
	public void noticeModifyGET() throws Exception {

		logger.info("공지사항 수정페이지 이동");
	}
	
	
	@PostMapping("/enroll.do")
	public String noticeEnrollPOST(NoticeVO notice ,RedirectAttributes rttr) throws Exception {
		 logger.info("공지사항 글 등록: " + notice);
		 
		 noticeservice.noticeRegister(notice);
		 rttr.addFlashAttribute("enroll_result", notice.getNoticeTitle());
		return "redirect:/notice/list";
	}
	
	
	/*
	@PostMapping("/enroll.do")
	public String noticeEnrollPOST(NoticeVO notice ,List<FileupVO> fileup, RedirectAttributes rttr) throws Exception {
		 logger.info("공지사항 글 등록: " + notice+", 파일들: "+ fileup);
		 
		 noticeservice.noticeRegisterfile(notice, fileup);
		 rttr.addFlashAttribute("enroll_result", notice.getNoticeTitle());
		return "redirect:/notice/list";
	}
	*/
}
