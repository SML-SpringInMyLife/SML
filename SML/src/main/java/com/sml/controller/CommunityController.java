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
import com.sml.model.Criteria;
import com.sml.model.PageDTO;
import com.sml.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {
   
   private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
   
   @Autowired
   private CommunityService service;
   
   @GetMapping("/boardList")
   public void boardListGET(Criteria cri, Model model) throws Exception {
      
      logger.info("커뮤니티 페이지 이동" +cri);
      
      List list = service.getBoardList(cri);
      
      if(!list.isEmpty()) {
			model.addAttribute("list",list);	
		} else {
			model.addAttribute("listCheck", "empty");	
		}
      
      int total = service.communityGetTotal(cri);
      PageDTO pageMaker = new PageDTO(cri, total);
      model.addAttribute("pageMaker", pageMaker);
//      model.addAttribute("pageMaker", new PageDTO(cri, service.communityGetTotal(cri)));
   }
   
   @GetMapping("/enroll")
   public void enrollGET() throws Exception{
	   logger.info("커뮤니티 글 등록");
   }
   @PostMapping("/enroll.do")
   public String enrollPOST(CommunityVO community, RedirectAttributes rttr) throws Exception {
	   logger.info("글 등록" +community);
	   service.communityEnroll(community);
	   rttr.addFlashAttribute("enroll_result", community.getCommTitle());
	   return "redirect:/community/boardList";
   }
   
   @GetMapping("/detail")
   public void communityDetail(int commCode, Criteria cri, Model model) throws Exception {
	   logger.info(commCode +"번 게시글 상세 페이지 이동");
	   model.addAttribute("cri", cri);
	   model.addAttribute("communityDetail", service.communityDetail(commCode));
   }
   
   @GetMapping("/modify")
   public void modifyGET() throws Exception{
	   logger.info("커뮤니티 글 수정");
   }
   @PostMapping("/modify")
   public String modifyPOST() throws Exception{
	   //해당 페이지로 다시 return - 바꾸기 
	   return "redirect:/community/boardList";
   }
   
   @PostMapping("/delete")
   public String deletePOST() throws Exception{
	   return "redirect:/community/boardList";
   }

}