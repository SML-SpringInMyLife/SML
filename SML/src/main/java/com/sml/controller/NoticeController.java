package com.sml.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.MemberVO;
import com.sml.model.NoticeVO;
import com.sml.model.PageDTO;
import com.sml.service.NoticeService;


@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
   
	@Autowired
	private NoticeService noticeservice;
	
	/* 공지사항 조회페이지 이동 */
	@GetMapping("/list")
	public void noticeListGET(Criteria cri, Model model, HttpSession session) throws Exception {

		logger.info("공지사항 조회페이지 이동" + cri);
      
		List<NoticeVO> list = noticeservice.noticeGetList(cri);
		
		if(!list.isEmpty()) {
	          model.addAttribute("list", list);
	       } else {
	    	   model.addAttribute("listCheck", "empty");
	       }
		
			/* 페이징처리 */
		int total = noticeservice.noticeGetTotal(cri);
		
		PageDTO pageMaker = new PageDTO(cri, total);
		
		model.addAttribute("pageMaker", pageMaker);
		
		
		 // 로그인 상태 확인
		// MemberVO 객체를 가져옵니다.
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    
	    // member 객체가 null이 아니라면 로그인 상태입니다.
	    boolean isLoggedIn = (member != null);
	    boolean isAdmin = false;
	    
	    if(isLoggedIn) {
	        isAdmin = member.getMemAdminCheck() == 1;
	    }
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    model.addAttribute("isAdmin", isAdmin);
	}
	
	/* 공지사항 조회기능 */
	@PostMapping("/Count")
	@ResponseBody
	public ResponseEntity<String> noticeCount(@RequestParam("noticeCode") int noticeCode) {
	    try {
	        int affectedRows = noticeservice.noticeCount(noticeCode);
	        logger.info("조회수 증가: 영향을 받은 행의 수 = " + affectedRows);
	        return ResponseEntity.ok("조회수 증가 성공");
	    } catch (Exception e) {
	        logger.error("조회수 증가 중 오류 발생", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패");
	    }
	}

	@PostMapping("/Like")
    @ResponseBody
    public ResponseEntity<String> noticeLikePOST(@RequestParam("noticeCode") int noticeCode) {
        try {
            boolean success = noticeservice.noticeLike(noticeCode);
            if (success) {
                return ResponseEntity.ok("좋아요 증가 성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 증가 실패");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("좋아요 증가 실패");
        }
    }
	
	/* 공지사항 상세조회페이지 이동 */
	@GetMapping({"/detail" , "/modify"})
	public void noticeDetailGET(int noticeCode,Criteria cri, Model model, HttpSession session) throws Exception {

		logger.info("공지사항 상세조회 이동 ");
			
		// 조회수 증가
        noticeservice.noticeCount(noticeCode);
		/* 공지사항 조회 페이지 정보 */
		model.addAttribute("cri",cri);
			/* 선택 글 정보 */
		model.addAttribute("noticeDetail", noticeservice.noticeGetDetail(noticeCode));
		 // 로그인 상태 확인
		// MemberVO 객체를 가져옵니다.
	    MemberVO member = (MemberVO) session.getAttribute("member");
	    
	    // member 객체가 null이 아니라면 로그인 상태입니다.
	    boolean isLoggedIn = (member != null);
	    boolean isAdmin = false;
	    
	    if(isLoggedIn) {
	        isAdmin = member.getMemAdminCheck() == 1;
	    }
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    model.addAttribute("isAdmin", isAdmin);
	}
	
	/* 공지사항 등록페이지 이동 */
	@GetMapping("/enroll")
	public void noticeEnrollGET(HttpSession session) throws Exception {

		logger.info("공지사항 등록페이지 이동");
     
	}
	
	@PostMapping("/enroll.do")
	public String noticeEnrollPOST(NoticeVO notice ,RedirectAttributes rttr) throws Exception {
		 logger.info("공지사항 글 등록: " + notice);
		 
		 noticeservice.noticeRegister(notice);
		 rttr.addFlashAttribute("enroll_result", notice.getNoticeTitle());
		return "redirect:/notice/list";
	}
  
	/* 공지사항 수정 */
	   @PostMapping("/modify")
	   public String noticeModifyPOST(NoticeVO notice ,RedirectAttributes rttr) throws Exception{
		   logger.info("공지사항 수정: " + notice); 
		   
		   int result= noticeservice.noticeModify(notice);
		   
		   rttr.addFlashAttribute("modify_result", result);
		   
		   return "redirect:/notice/list";
	   }
	
		/* 공지사항 삭제 */
	   
	   @PostMapping("/delete")
	   public String noticeDeletePOST(int noticeCode,RedirectAttributes rttr) throws Exception{
		  
			logger.info("공지사항 삭제.........." + noticeCode);
			
			int result = 0;
			
			try {
				
				result = noticeservice.noticeDelete(noticeCode);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				result = 2;
				rttr.addFlashAttribute("delete_result", result);
				
				return "redirect:/notice/list";
			}

			logger.info("삭제 결과.........." + result);
			rttr.addFlashAttribute("delete_result", result);
			
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
