package com.sml.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;
import com.sml.model.MemberCheckVO;
import com.sml.model.MemberVO;
import com.sml.model.PageDTO;
import com.sml.model.PointVO;
import com.sml.service.MemberService;
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
    	
    	System.out.println("updateMemberPOST : " + member);
    	
    	String rawPw = ""; //인코딩전 비밀번호
  		String encodePw = ""; //인코딩후 비밀번호
  		
  		rawPw = member.getMemPw();
  		encodePw = pwEncoder.encode(rawPw);
  		member.setMemPw(encodePw);     	
  		
    	service.updateMember(member);    
    	
    	HttpSession session = request.getSession(false);
    	session.setAttribute("member", member);
    	
    	return "redirect:/";
    	
    	
    }
    
	
	/* 회원 수강신청 페이지 이동 */
	@GetMapping("mycourses")
	public void memberCoursesGET( Criteria criteria, HttpServletRequest request) throws Exception {

		logger.info("회원 수강신청 페이지 이동");
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");			
		
			logger.info("getPageNum = " + criteria.getPageNum());
			logger.info("getAmount = " + criteria.getAmount());			
			
		List<CourseApplyDTO> list = service.selectCourseList(loginMember.getMemCode(), criteria);
		PageDTO pageInfo = service.getCourseTotalCount(loginMember.getMemCode(), criteria);
		
		request.setAttribute("list", list);
		request.setAttribute("pageInfo", pageInfo);
			
		}

	}
	
	/* 회원 포인트 페이지 이동 */
	@GetMapping("point")	
	public void memberPointGET(@RequestParam(required = false) String selectDate,
            Criteria criteria,
            HttpServletRequest request) throws Exception {

		logger.info("회원 포인트 페이지 이동");
		logger.info("selectDate before = " + selectDate);
		
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");	
			
			logger.info("getPageNum = " + criteria.getPageNum());
			logger.info("getAmount = " + criteria.getAmount());	
			
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
			int TotalPoint = service.selectTotalPoint(loginMember.getMemCode());
			List<PointVO> list = service.selectPointList(loginMember.getMemCode(),strDate, criteria);
			PageDTO pageInfo = service.getTotalCount(loginMember.getMemCode(),strDate, criteria);
			
			request.setAttribute("TotalPoint", TotalPoint);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);

		}
		
	}
	
	// 회원 출석체크 페이지 이동
    @GetMapping("memberCheck")
    public void memberDailyCheckGET(String selectDate)throws Exception {
        logger.info("회원 출석체크 페이지 이동");
        
		logger.info("selectDate before = " + selectDate);
		
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("member") != null) {
			MemberVO loginMember = (MemberVO)session.getAttribute("member");		
			
			int memCode = loginMember.getMemCode();				
			
			// 현재 년월을 가져옵니다 (yyyy-MM 형식)
	        String currentYearMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
	        
	        // selectDate가 null이면 현재 년월을 사용합니다
	        String yearMonth = (selectDate != null) ? selectDate : currentYearMonth;
	        
	        List<MemberCheckVO> checkList = service.selectMemberCheckList(memCode, yearMonth);
	        logger.info("checkList = " + checkList);
	        request.setAttribute("checkList", checkList);
	/*	
	     // 오늘 날짜에 대해 자동으로 출석 처리
	        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        if (checkList.stream().noneMatch(check -> check.getCheckDate().equals(today))) {
	            // 출석이 처리되지 않은 경우
	            MemberCheckVO todayCheck = new MemberCheckVO();
	            todayCheck.setMemCode(memCode);
	            
	            service.insertMemberCheck(todayCheck);
	        }
	*/	
		}
        
    }
}
	
	 
  
