package com.sml.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;
import com.sml.service.MemberService;
import com.sml.service.MypageService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
  //MemberService가 MemberController에 자동주입되도록 코드추가
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MypageService service;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private BCryptPasswordEncoder pwEncoder;
    
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
  		System.out.println("회원가입 데이터 : " + member);
  		
  		String rawPw = ""; //인코딩전 비밀번호
  		String encodePw = ""; //인코딩후 비밀번호
  		
  		rawPw = member.getMemPw();
  		encodePw = pwEncoder.encode(rawPw);
  		member.setMemPw(encodePw);  		
  		
  		if (member.getMemJoinDate() == null) {
  			Date now = new Date();
  			member.setMemJoinDate(now);
  			member.setMemQuitDate(now);
  		}
  		
  		//회원가입 서비스 실행
  		memberService.MemberJoin(member);
  		
  		logger.info("로그인 서비스 성공");
  		
  		return "redirect:/";
  	}
  	
  //아이디 중복 검사
  	@PostMapping("/memberIdChk")
  	@ResponseBody
  	public String memberIdChkPOST(String memId) throws Exception{
  		//logger.info("memberIdChk() 진입 :" + memId);
  		
  		int result = memberService.idCheck(memId);
  		
  		logger.info("결과값 = " + result );
  		
  		if(result != 0) {
  			return "fail"; //중복 아이디가 존재
  		}else {
  			return "success"; 
  		}
  	} 
  	
  	//이메일 인증//
  	@GetMapping("/mailCheck")
  	@ResponseBody
  	public String mailCheckGET(String email) throws Exception{
  		
  		logger.info("이메일 데이터 전송 확인");
  		logger.info("인증번호:" + email);
  		
  		//인증번호 난수 생성//
  		Random random = new Random();
  		int checkNum = random.nextInt(888888) + 111111;
  		logger.info("인증번호" + checkNum);
  		
  		//이메일 보내기//
  		String setFrom = "jin22636@naver.com";
  		String tomail = email;
  		String title = "회원가입 인증 이메일";
  		String content = 
  				"SML 홈페이지에 오신 것을 환영합니다!!" + "<br><br>" +
  				"인증번호는 " + checkNum + " 입니다" + "<br>" +
  				"해당 인증번호를 인증번호 확인란에 기입하여 주세요";
  		
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
  	/* 로그인 */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        System.out.println("login 메서드 진입");
        System.out.println("전달된 데이터 : " + member);
    	
    	HttpSession session = request.getSession();
    	String rawPw = "";
    	String encodePw = "";
    	 
    	 
    	 MemberVO lvo = memberService.memberLogin(member); //제출한 아이디와 일치하는 아이디 있는지
    	 
    	 System.out.println("111 : " + lvo);
    	 
    	    	 
    	 if(lvo != null) {
    		 rawPw = member.getMemPw();
    		 encodePw = lvo.getMemPw();
    		 
    		 if(true == pwEncoder.matches(rawPw, encodePw)) {
    			
    			 lvo.setMemPw("");
    			 session.setAttribute("member", lvo);
    			 session.setAttribute("memCode", lvo.getMemCode());
    			 session.setAttribute("isAdmin", lvo.getMemAdminCheck() == 1);
    			 
    			 // 로그인성공시 자동출석체크 
    			   //출석체크여부확인(memberCheckTest)
    				String result = memberCheckTest(lvo.getMemCode());
    				
    				//rttr.addFlashAttribute("usePoint", 100);
    				
    				//출석 되어 있지 않으면,
    				if (result == "success") {
    					MemberCheckVO vo = new MemberCheckVO();
    					vo.setCheckDate(new Date());		
    					vo.setStatus(1);
    					vo.setMemCode(lvo.getMemCode());
    					// 출석체크 등록 서비스 호출 
    					service.insertMemberCheck(vo);	
    					
    					try {
    					    //출석체크시 자동 포인트 적립
    						int TotalPoint = service.selectTotalPoint(lvo.getMemCode());
    						int usePoint = 50;
    						TotalPoint = TotalPoint + usePoint;
    						lvo.setMemTotalPoint(TotalPoint);
    					    
    					    PointVO point = new PointVO();
    					    point.setPointPrice(usePoint);
    					    point.setPointDate(new Date());
    					    point.setPointComment("출석체크 포인트 적립");
    					    point.setStatus(1); //1은 적립, 2는 사용
    					    point.setMemCode(lvo.getMemCode());				    
    						
    						service.updateTotalPoint(lvo);
    						
    						System.out.println("point : " + point);
    						service.insertPoint(point);
    						
    						//request.setAttribute("usePoint", usePoint);
    						rttr.addFlashAttribute("usePoint", usePoint);
    						
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}				
    				}
    			
    			 //
    			 return "redirect:/";
    		 }else {
    			 rttr.addFlashAttribute("result", 0);
                 return "redirect:/member/login";
    		 }
    	 }else { //일치하는 아이디가 존재하지 않을때 (로그인실패)
    		 rttr.addFlashAttribute("result", 0);
             return "redirect:/member/login";
    	 }	    		 
   
    }
    
  //출석체크 중복 검사
  	//@PostMapping("/memberCheckTest")
  	//@ResponseBody
  	public String memberCheckTest(int memberCode){
  		
  	    logger.info("중복체크검사 진입");
			
		String strDate;

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		strDate = format.format(date);			

  		int result = service.memberCheckTest(memberCode, strDate);
  	  		
  		logger.info("중복 검사 결과 = " + result );

		if(result > 0) {
  			return "fail"; //중복된 출석이 존재
  		} else {
  			return "success"; //중복 없음
		}
  	}
    
  //네이버 로그인
  	//@RequestMapping(value="/naverLogin", method=RequestMethod.GET)
	@GetMapping("naverLogin")
    public void callBack(){
  		System.out.println("네이버로그인 콜백 진입");
  		//return "naverLogin";
    } 
    
    /* 로그아웃 */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
    	
    	System.out.println("로그아웃 메서드 진입");    	
    	
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/"; 
    }
	

  
    /*
    @ResponseBody
    public Map<String, String> updateMember(@RequestParam String memMail,
    		                                @RequestParam String memPhone,
    		                                @RequestParam String memEmerPhone,
    		                                @RequestParam String memAddr1,
    		                                @RequestParam String memAddr2,
    		                                @RequestParam String memAddr3,
                                            HttpSession session) {
    	Map<String, String> response = new HashMap<>();
        
        // 세션에서 회원 ID를 가져옵니다. (세션에 'memId'라는 이름으로 저장되어 있다고 가정)
        Integer memId = (Integer) session.getAttribute("memId");
        
        if (memId == null) {
            // 세션에 memId가 없으면 에러 응답을 반환합니다.
            response.put("status", "error");
            response.put("message", "Session expired or invalid.");
            return response;
        }

        // 여기서 서비스 레이어를 호출하여 회원 정보를 업데이트합니다.
        try {
            MemberVO member = new MemberVO();           
            member.setMemMail(memMail);
            member.setMemPhone(memPhone);
            member.setMemEmerPhone(memEmerPhone);
            member.setMemAddr1(memAddr1);
            member.setMemAddr2(memAddr2);
            member.setMemAddr3(memAddr3);

            // 예시로 memberService.updateMember를 호출한다고 가정합니다.
            memberService.updateMember(member);

            response.put("status", "success");
            response.put("message", "업데이트 성공");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "업데이트 실패");
        }

        return response;
        */
    //}
    
    
  
}