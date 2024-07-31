package com.sml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sml.model.MemberVO;
import com.sml.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
  //MemberService가 MemberController에 자동주입되도록 코드추가
    @Autowired
    private MemberService memberService;
    
  //회원가입 페이지 이동  		
  	@GetMapping("join")
  	public void joinGET() {
  		logger.info("회원가입 페이지 진입");
  	}		
  	
  //로그인 페이지 이동
  	@GetMapping("login")
  	public void loginGET() {
  		logger.info("로그인 페이지 진입");
  	}
  	
  //회원가입
  	@PostMapping("/join")
  	public String joinPOST(MemberVO member) throws Exception{
  		
  		logger.info("회원가입 진입");
  		
  		//회원가입 서비스 실행
  		memberService.MemberJoin(member);
  		
  		logger.info("로그인 서비스 성공");
  		
  		return "redirect:/";
  	}

	
}
