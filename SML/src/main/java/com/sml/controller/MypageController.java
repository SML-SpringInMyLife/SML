package com.sml.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.service.MypageService;

@Controller
@RequestMapping("/member")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private MypageService service;
	
	@Autowired
	private HttpServletRequest request;
	
	
	/* 회원 마이페이지 이동 */
	@GetMapping("mypage")
	public void memberMypageGET() throws Exception {

		logger.info("회원 마이페이지 이동");

	}
	
	/* 회원정보 페이지 이동*/
	@GetMapping("myinfo")
	public void memberMyinfoGET() throws Exception {

		logger.info("회원정보 페이지 이동");

	}
	
	/* 회원 수강신청 페이지 이동 */
	@GetMapping("mycourses")
	public void memberCoursesGET() throws Exception {

		logger.info("회원 수강신청 페이지 이동");

	}
	
	/* 회원 적립금 페이지 이동 */
	@GetMapping("point")
	public void memberPointGET() throws Exception {

		logger.info("회원 적립금 페이지 이동");

	}
	
	// 회원 출석체크 페이지 이동
    @GetMapping("memberCheck")
    public void memberDailyCheckGET() {
        logger.info("회원 출석체크 페이지 이동");
        
    }

    // 출석체크 등록
    @PostMapping("/memberCheck")
    public void insertMemberCheckPOST(MemberCheckVO membercheck) {
    	logger.info("MemberCheckVO" + membercheck );
    	
    	HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");
			
			int MemCode = loginMember.getMemCode();
			
			MemberCheckVO vo = new MemberCheckVO();
			vo.setCheckDate(new Date());		
			vo.setStatus(1);
			vo.setMemCode(MemCode);
			
			service.insertMemberCheck(vo);

		}
    }
    

    
}
	
	 
  
