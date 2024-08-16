package com.sml.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.sml.model.MemberVO;
import com.sml.model.PageDTO;
import com.sml.model.ReplyDTO;
import com.sml.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService service;

	@GetMapping("/boardList")
	public void boardListGET(Criteria cri, Model model) throws Exception {

		logger.info("커뮤니티 페이지 이동" + cri);

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
		logger.info("커뮤니티 글 등록");
	}

	@PostMapping("/enroll.do")
	public String enrollPOST(CommunityVO community, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		MemberVO loggedInUser = (MemberVO) session.getAttribute("loggedInUser");

	    if (loggedInUser != null) {
	        // 로그인한 사용자의 회원 코드 가져오기
	        int memCode = loggedInUser.getMemCode();
	        // CommunityVO 객체에 회원 코드 설정
	        community.setMemCode(memCode);
	    }
		
		service.communityEnroll(community);
		rttr.addFlashAttribute("enroll_result", community.getCommTitle());
		return "redirect:/community/boardList";
	}

	@GetMapping("/detail")
	public void communityDetailGET(int commCode, Criteria cri, Model model) throws Exception {
		logger.info(commCode + "번 게시글 상세 페이지 이동");
		model.addAttribute("cri", cri);
		model.addAttribute("communityDetail", service.communityDetail(commCode));
		
		// 댓글 영역
		List list = service.listReply();
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
		}
		
		int total = service.communityGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	@GetMapping("/modify")
	public void communityModifyGET(int commCode, Criteria cri, Model model) throws Exception {
		logger.info(commCode + "번 게시글 상세 페이지 이동");
		model.addAttribute("cri", cri);
		model.addAttribute("communityDetail", service.communityDetail(commCode));
	}
	@PostMapping("/modify")
	public String modifyPOST(CommunityVO community, RedirectAttributes rttr) throws Exception {
		logger.info("modifyPOST......" + community);
		int result = service.communityModify(community);
		rttr.addFlashAttribute("modify_result", result);
		// 해당 페이지로 다시 return - 바꾸기
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
	
	
	// 댓글
	@PostMapping("/reply/enroll")
	public void enrollReplyPOST(ReplyDTO dto) throws Exception {
		service.enrollReply(dto);
	}
	
//	@GetMapping("/reply/modify")
//	public void replyModifyGET(int repCode, Criteria cri, Model model) {
//		model.addAttribute("replyDetail", service.replyModify(repCode));
//	}
//	@PostMapping("/reply/modify")
//	public String replyModifyGET(ReplyDTO reply, RedirectAttributes rttr) throws Exception{
//		int result = service.replyModify(reply);
//		rttr.addFlashAttribute("reply_modify_result", result);
//		return "redirect:/detail";
//	}
//	
//	@PostMapping("/delete")
//	public String replyDeletePOST(int repCode, RedirectAttributes rttr) throws Exception {
//		logger.info("deletePOST......");
//		int result = 0;
//		try {
//			result = service.replyDelete(repCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result = 2;
//			rttr.addFlashAttribute("reply_delete_result", result);
//			return "redirect:/community/detail";
//		}
//		rttr.addFlashAttribute("reply_delete_result", result);
//		return "redirect:/community/detail";
//	}
	
}