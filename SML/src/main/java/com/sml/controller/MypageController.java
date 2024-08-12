package com.sml.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PointVO;
import com.sml.service.MypageService;

@Controller
@RequestMapping("/member")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private MypageService service;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
    private BCryptPasswordEncoder pwEncoder;
	
	
	/* 회원 마이페이지 이동 */
	@GetMapping("mypage")
	public void memberMypageGET() throws Exception {

		logger.info("회원 마이페이지 이동");

	}
	
	/* 회원정보 페이지 이동*/
	@GetMapping("myinfo")
	public String memberMyinfoGET() throws Exception {

		logger.info("회원정보 페이지 이동");
		
    	HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");
			//logger.info("loginMember=" + loginMember.getMemName());
			request.setAttribute("Member", loginMember);

		}
		return "/member/myinfo";
	}
	
	 /*회원정보수정(수정된 정보를 저장하는 역할)*/
    @PostMapping("/updateMember")
    public String updateMemberPOST(MemberVO member)throws Exception{
    	
    	String rawPw = ""; //인코딩전 비밀번호
  		String encodePw = ""; //인코딩후 비밀번호
  		
  		rawPw = member.getMemPw();
  		encodePw = pwEncoder.encode(rawPw);
  		member.setMemPw(encodePw); 
    	
    	
    	//service.updateMember(member);
    	service.updateMember(member);
    	return "redirect:/";
    	
    	
    }
    
	
	/* 회원 수강신청 페이지 이동 */
	@GetMapping("mycourses")
	public void memberCoursesGET() throws Exception {

		logger.info("회원 수강신청 페이지 이동");

	}
	
	/* 회원 포인트 페이지 이동 */
	@GetMapping("point")	
	public void memberPointGET(String selectDate) throws Exception {

		logger.info("회원 적립금 페이지 이동");
		logger.info("selectDate before = " + selectDate);
		
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");		
			
			String strDate;
			
			if(selectDate == null || selectDate == "") {
			  Date date = new Date();
		      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		      strDate = format.format(date);
				
			}else {
			  strDate = selectDate; 
			  strDate = strDate.substring(0,4) + "-" + strDate.substring(4,strDate.length());
			  logger.info("selectDate after = " + selectDate);
			}			
			
			List<PointVO> list = service.selectPointList(loginMember.getMemCode(),strDate);
			int totalPoint = service.selectTotalPoint(loginMember.getMemCode());

			request.setAttribute("list", list);
			request.setAttribute("TotalPoint", totalPoint);
		}
		
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
	
	 
  
