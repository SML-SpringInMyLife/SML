package com.sml.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.PageDTO;
import com.sml.service.CommunityReplyService;
import com.sml.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService service;
	
	@Autowired
	private CommunityReplyService replyService;

	@GetMapping("/boardList")
	public void boardListGET(Criteria cri, Model model) throws Exception {

		List list = service.getBoardList(cri);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			model.addAttribute("totalCount", list.size());
		} else {
			model.addAttribute("listCheck", "empty");
		}

		int total = service.communityGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
//      model.addAttribute("pageMaker", new PageDTO(cri, service.communityGetTotal(cri)));
	}

	@GetMapping("/enroll")
	public void enrollGET() throws Exception {
		logger.info("而ㅻ�ㅻ땲�떚 湲� �벑濡�");
	}

	@PostMapping("/enroll.do")
	public String enrollPOST(CommunityVO community, RedirectAttributes rttr, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("member");
		if (member == null) {
	        rttr.addFlashAttribute("error_message", "濡쒓렇�씤�빐 二쇱꽭�슂.");
	        return "redirect:/community/boardList";
	    }
		
		int memCode = (Integer) session.getAttribute("memCode");
		community.setMemCode(memCode);
		
		service.communityEnroll(community);

		service.communityPoint(member);
		rttr.addFlashAttribute("enroll_result", community.getCommTitle());
		return "redirect:/community/boardList";
	}

	@GetMapping({"/detail", "/modify"})
	public void communityDetailGET(int commCode, Criteria cri, Model model) throws Exception {

		model.addAttribute("cri", cri);
		model.addAttribute("communityDetail", service.communityDetail(commCode));
		
		int total = service.communityGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);

	}

	@PostMapping("/modify")
	public String modifyPOST(CommunityVO community, RedirectAttributes rttr) throws Exception {
		logger.info("modifyPOST......" + community);
		int result = service.communityModify(community);
		rttr.addFlashAttribute("modify_result", result);
		// �빐�떦 �럹�씠吏�濡� �떎�떆 return - 諛붽씀湲�
		return "redirect:/community/boardList";
	}

	@PostMapping("/delete")
	public String deletePOST(int commCode, RedirectAttributes rttr) throws Exception {
		logger.info("deletePOST......");
		int result = 0;
		try {
			result = service.communityDelete(commCode);
		} catch (Exception e) {
			e.printStackTrace();
			result = 2;
			rttr.addFlashAttribute("delete_result", result);
			return "redirect:/community/boardList";
		}

		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/community/boardList";
	}
	
	@GetMapping("/reply/{memCode}")
	public String replyEnrollGET(@PathVariable("memCode")int memCode, int commCode, Model model) {
		CommunityVO community = service.getCommCode(commCode);
		model.addAttribute("commCode", community);
		model.addAttribute("memCode", memCode);
		
		return "/reply";
	}

}