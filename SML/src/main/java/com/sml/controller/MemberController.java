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
	
  //MemberService°¡ MemberController¿¡ ÀÚµ¿ÁÖÀÔµÇµµ·Ï ÄÚµåÃß°¡
    @Autowired
    private MemberService memberService;
    
<<<<<<< HEAD
  //È¸¿ø°¡ÀÔ ÆäÀÌÁö ÀÌµ¿  		
=======
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private BCryptPasswordEncoder pwEncoder;
    
  //íšŒì›ê°€ì… í˜ì´ì§€ ì´ë™  		
>>>>>>> member
  	@GetMapping("join")
  	public void joinGET() {
  		logger.info("È¸¿ø°¡ÀÔ ÆäÀÌÁö ÁøÀÔ");
  	}		
  	
  //·Î±×ÀÎ ÆäÀÌÁö ÀÌµ¿
  	@GetMapping("login")
  	public void loginGET() {
  		logger.info("·Î±×ÀÎ ÆäÀÌÁö ÁøÀÔ");
  	}
  	
  //È¸¿ø°¡ÀÔ
  	@PostMapping("/join")
  	public String joinPOST(MemberVO member) throws Exception{
  		
<<<<<<< HEAD
  		logger.info("È¸¿ø°¡ÀÔ ÁøÀÔ");
=======
  		logger.info("íšŒì›ê°€ì… ì§„ì…");
  		System.out.println("íšŒì›ê°€ì… ë°ì´í„° : " + member);
  		
  		String rawPw = ""; //ì¸ì½”ë”©ì „ ë¹„ë°€ë²ˆí˜¸
  		String encodePw = ""; //ì¸ì½”ë”©í›„ ë¹„ë°€ë²ˆí˜¸
  		
  		rawPw = member.getMemPw();
  		encodePw = pwEncoder.encode(rawPw);
  		member.setMemPw(encodePw);  		
  		
  		if (member.getMemJoinDate() == null) {
  			Date now = new Date();
  			member.setMemJoinDate(now);
  			member.setMemQuitDate(now);
  		}
>>>>>>> member
  		
  		//È¸¿ø°¡ÀÔ ¼­ºñ½º ½ÇÇà
  		memberService.MemberJoin(member);
  		
  		logger.info("·Î±×ÀÎ ¼­ºñ½º ¼º°ø");
  		
  		return "redirect:/";
  	}
<<<<<<< HEAD

  	/* ·Î±×ÀÎ */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        System.out.println("login ¸Ş¼­µå ÁøÀÔ");
        System.out.println("Àü´ŞµÈ µ¥ÀÌÅÍ : " + member);
=======
  	
  //ì•„ì´ë”” ì¤‘ë³µ ê²€ì‚¬
  	@PostMapping("/memberIdChk")
  	@ResponseBody
  	public String memberIdChkPOST(String memId) throws Exception{
  		//logger.info("memberIdChk() ì§„ì… :" + memId);
  		
  		int result = memberService.idCheck(memId);
  		
  		logger.info("ê²°ê³¼ê°’ = " + result );
  		
  		if(result != 0) {
  			return "fail"; //ì¤‘ë³µ ì•„ì´ë””ê°€ ì¡´ì¬
  		}else {
  			return "success"; 
  		}
  	} 
  	
  	//ì´ë©”ì¼ ì¸ì¦//
  	@GetMapping("/mailCheck")
  	@ResponseBody
  	public String mailCheckGET(String email) throws Exception{
  		
  		logger.info("ì´ë©”ì¼ ë°ì´í„° ì „ì†¡ í™•ì¸");
  		logger.info("ì¸ì¦ë²ˆí˜¸:" + email);
  		
  		//ì¸ì¦ë²ˆí˜¸ ë‚œìˆ˜ ìƒì„±//
  		Random random = new Random();
  		int checkNum = random.nextInt(888888) + 111111;
  		logger.info("ì¸ì¦ë²ˆí˜¸" + checkNum);
  		
  		//ì´ë©”ì¼ ë³´ë‚´ê¸°//
  		String setFrom = "jin22636@naver.com";
  		String tomail = email;
  		String title = "íšŒì›ê°€ì… ì¸ì¦ ì´ë©”ì¼";
  		String content = 
  				"SML í™ˆí˜ì´ì§€ì— ì˜¤ì‹  ê²ƒì„ í™˜ì˜í•©ë‹ˆë‹¤!!" + "<br><br>" +
  				"ì¸ì¦ë²ˆí˜¸ëŠ” " + checkNum + " ì…ë‹ˆë‹¤" + "<br>" +
  				"í•´ë‹¹ ì¸ì¦ë²ˆí˜¸ë¥¼ ì¸ì¦ë²ˆí˜¸ í™•ì¸ë€ì— ê¸°ì…í•˜ì—¬ ì£¼ì„¸ìš”";
  		
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
  	/* ë¡œê·¸ì¸ */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        //System.out.println("login ë©”ì„œë“œ ì§„ì…");
        //System.out.println("ì „ë‹¬ëœ ë°ì´í„° : " + member);
>>>>>>> member
    	
    	HttpSession session = request.getSession();
    	String rawPw = "";
    	String encodePw = "";
    	 
    	 
    	 MemberVO lvo = memberService.memberLogin(member); //ì œì¶œí•œ ì•„ì´ë””ì™€ ì¼ì¹˜í•˜ëŠ” ì•„ì´ë”” ìˆëŠ”ì§€
    	 
    	 System.out.println("111 : " + lvo);
    	 
<<<<<<< HEAD
    	 if(lvo == null) {                    // ÀÏÄ¡ÇÏÁö ¾Ê´Â ¾ÆÀÌµğ, ºñ¹Ğ¹øÈ£ ÀÔ·Â °æ¿ì
             
             int result = 0;
             rttr.addFlashAttribute("result", result);
             return "redirect:/member/login";             
         }
         
         session.setAttribute("member", lvo);    // ÀÏÄ¡ÇÏ´Â ¾ÆÀÌµğ, ºñ¹Ğ¹øÈ£ °æ¿ì (·Î±×ÀÎ ¼º°ø)
         
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
    	 }else { //ì¼ì¹˜í•˜ëŠ” ì•„ì´ë””ê°€ ì¡´ì¬í•˜ì§€ ì•Šì„ë•Œ (ë¡œê·¸ì¸ì‹¤íŒ¨)
    		 rttr.addFlashAttribute("result", 0);
             return "redirect:/member/login";
    	 }	    		 
   
    }
    
    /* ë¡œê·¸ì•„ì›ƒ */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
    	
    	System.out.println("ë¡œê·¸ì•„ì›ƒ ë©”ì„œë“œ ì§„ì…");    	
    	
        HttpSession session = request.getSession(false);
>>>>>>> member
        
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/"; 
    }
	
}