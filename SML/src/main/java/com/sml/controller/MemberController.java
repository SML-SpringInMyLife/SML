package com.sml.controller;

import java.util.Date;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.MemberVO;
import com.sml.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
  //MemberService가 MemberController에 자동주입되도록 코드추가
    @Autowired
    private MemberService memberService;
    
<<<<<<< HEAD
  //회원가입 페이지 이동  		
=======
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private BCryptPasswordEncoder pwEncoder;
    
  //�쉶�썝媛��엯 �럹�씠吏� �씠�룞  		
>>>>>>> member
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
  		
<<<<<<< HEAD
  		logger.info("회원가입 진입");
=======
  		logger.info("�쉶�썝媛��엯 吏꾩엯");
  		System.out.println("�쉶�썝媛��엯 �뜲�씠�꽣 : " + member);
  		
  		String rawPw = ""; //�씤肄붾뵫�쟾 鍮꾨�踰덊샇
  		String encodePw = ""; //�씤肄붾뵫�썑 鍮꾨�踰덊샇
  		
  		rawPw = member.getMemPw();
  		encodePw = pwEncoder.encode(rawPw);
  		member.setMemPw(encodePw);  		
  		
  		if (member.getMemJoinDate() == null) {
  			Date now = new Date();
  			member.setMemJoinDate(now);
  			member.setMemQuitDate(now);
  		}
>>>>>>> member
  		
  		//회원가입 서비스 실행
  		memberService.MemberJoin(member);
  		
  		logger.info("로그인 서비스 성공");
  		
  		return "redirect:/";
  	}
<<<<<<< HEAD

  	/* 로그인 */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        System.out.println("login 메서드 진입");
        System.out.println("전달된 데이터 : " + member);
=======
  	
  //�븘�씠�뵒 以묐났 寃��궗
  	@PostMapping("/memberIdChk")
  	@ResponseBody
  	public String memberIdChkPOST(String memId) throws Exception{
  		//logger.info("memberIdChk() 吏꾩엯 :" + memId);
  		
  		int result = memberService.idCheck(memId);
  		
  		logger.info("寃곌낵媛� = " + result );
  		
  		if(result != 0) {
  			return "fail"; //以묐났 �븘�씠�뵒媛� 議댁옱
  		}else {
  			return "success"; 
  		}
  	} 
  	
  	//�씠硫붿씪 �씤利�//
  	@GetMapping("/mailCheck")
  	@ResponseBody
  	public String mailCheckGET(String email) throws Exception{
  		
  		logger.info("�씠硫붿씪 �뜲�씠�꽣 �쟾�넚 �솗�씤");
  		logger.info("�씤利앸쾲�샇:" + email);
  		
  		//�씤利앸쾲�샇 �궃�닔 �깮�꽦//
  		Random random = new Random();
  		int checkNum = random.nextInt(888888) + 111111;
  		logger.info("�씤利앸쾲�샇" + checkNum);
  		
  		//�씠硫붿씪 蹂대궡湲�//
  		String setFrom = "jin22636@naver.com";
  		String tomail = email;
  		String title = "�쉶�썝媛��엯 �씤利� �씠硫붿씪";
  		String content = 
  				"SML �솃�럹�씠吏��뿉 �삤�떊 寃껋쓣 �솚�쁺�빀�땲�떎!!" + "<br><br>" +
  				"�씤利앸쾲�샇�뒗 " + checkNum + " �엯�땲�떎" + "<br>" +
  				"�빐�떦 �씤利앸쾲�샇瑜� �씤利앸쾲�샇 �솗�씤���뿉 湲곗엯�븯�뿬 二쇱꽭�슂";
  		
  		try {
  			MimeMessage message = mailSender.createMimeMessage();
  			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
  			helper.setFrom(setFrom);
  			helper.setTo(tomail);
  			helper.setSubject(title);
  			helper.setText(content,true);
  			mailSender.send(message);  			
  		}catch(Exception e){
  			e.printStackTrace();
  			
  		}
  		String num = Integer.toString(checkNum);
  		return num;
  		
  	}
  	/* 濡쒓렇�씤 */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        //System.out.println("login 硫붿꽌�뱶 吏꾩엯");
        //System.out.println("�쟾�떖�맂 �뜲�씠�꽣 : " + member);
>>>>>>> member
    	
    	HttpSession session = request.getSession();
    	String rawPw = "";
    	String encodePw = "";
    	 
    	 
    	 MemberVO lvo = memberService.memberLogin(member); //�젣異쒗븳 �븘�씠�뵒�� �씪移섑븯�뒗 �븘�씠�뵒 �엳�뒗吏�
    	 
    	 System.out.println("111 : " + lvo);
    	 
<<<<<<< HEAD
    	 if(lvo == null) {                    // 일치하지 않는 아이디, 비밀번호 입력 경우
             
             int result = 0;
             rttr.addFlashAttribute("result", result);
             return "redirect:/member/login";             
         }
         
         session.setAttribute("member", lvo);    // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
         
         return "redirect:/";        
=======
    	    	 
    	 if(lvo != null) {
    		 rawPw = member.getMemPw();
    		 encodePw = lvo.getMemPw();
    		 
    		 if(true == pwEncoder.matches(rawPw, encodePw)) {
    			 
    			 lvo.setMemPw("");
    			 session.setAttribute("member", lvo);
    			 return "redirect:/";
    		 }else {
    			 rttr.addFlashAttribute("result", 0);
                 return "redirect:/member/login";
    		 }
    	 }else { //�씪移섑븯�뒗 �븘�씠�뵒媛� 議댁옱�븯吏� �븡�쓣�븣 (濡쒓렇�씤�떎�뙣)
    		 rttr.addFlashAttribute("result", 0);
             return "redirect:/member/login";
    	 }	    		 
   
    }
    
    /* 濡쒓렇�븘�썐 */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
    	
    	System.out.println("濡쒓렇�븘�썐 硫붿꽌�뱶 吏꾩엯");    	
    	
        HttpSession session = request.getSession(false);
>>>>>>> member
        
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/"; 
    }
	
}