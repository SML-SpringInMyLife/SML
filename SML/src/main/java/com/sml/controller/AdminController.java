package com.sml.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.ChatVO;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.PageDTO;
import com.sml.model.SmsVO;
import com.sml.service.AdminService;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService service;

	@Value("${SMS_KEY}")
	private String SMSapiKey;

	@Value("${SMS_SecretKEY}")
	private String SMSapiSecret;

	/* 관리자 메인 페이지 이동 */
	@GetMapping(value = "main")
	public void adminMainGET(Model model) throws Exception {
		logger.info("관리자 페이지 이동");

		int memberCnt = service.getMemberCnt();
		model.addAttribute("memberCnt", memberCnt);

		Map<String, Integer> ageGroupCnt = service.getAgeGroupCnt();
		model.addAttribute("ageGroupCnt", ageGroupCnt);

		// 기본 연도에 대한 차트 데이터 추가
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // 기본 연도
		Map<String, int[]> chartData = service.getAgeGroupCountsByMonth(year);

		// 차트 데이터를 모델에 추가
		model.addAttribute("chartData", chartData);
	}

	@GetMapping(value = "getDataForYear")
	@ResponseBody
	public Map<String, int[]> getDataForYear(@RequestParam("year") String year) throws Exception {
		logger.info("선택 년도 : " + year);
		Map<String, int[]> result = service.getAgeGroupCountsByMonth(year);
		return result;
	}

	@GetMapping(value = "members")
	public void adminMembersGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 회원관리페이지 이동");

		// Criteria 객체를 이용하여 회원 목록 가져오기
		List<MemberVO> members = service.getMemberList(cri);
		if (!members.isEmpty()) {
			model.addAttribute("members", members);
			model.addAttribute("totalCount", members.size());
		} else {
			model.addAttribute("listCheck", "empty");
		}

		// 전체 회원 수와 페이지 DTO 계산
		int total = service.memberGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}

	@PostMapping(value = "updateAdm")
	public String updateAdm(@RequestParam int memCode, @RequestParam int memAdminCheck, RedirectAttributes rttr) {
		logger.info("관리권한 업데이트 멤버Code: " + memCode + ", 관리자 여부: " + memAdminCheck);

		try {
			service.updateAdm(memCode, memAdminCheck);
			rttr.addFlashAttribute("result", "success");
		} catch (Exception e) {
			logger.error("업데이트 실패 : ", e);
			rttr.addFlashAttribute("result", "fail");
		}

		return "redirect:/admin/members";
	}

	@PostMapping(value = "updateStatus")
	public String updateStatus(@RequestParam int memCode, @RequestParam int memStatus, RedirectAttributes rttr) {
		logger.info("상태 업데이트 멤버Code: " + memCode + ", 상태: " + memStatus);

		try {
			service.updateStatus(memCode, memStatus);
			rttr.addFlashAttribute("result", "success");
		} catch (Exception e) {
			logger.error("업데이트 실패 : ", e);
			rttr.addFlashAttribute("result", "fail");
		}

		return "redirect:/admin/members";
	}

	@GetMapping(value = "courses")
	public void adminCoursesGET(Criteria cri, Model model) throws Exception {

		logger.info("관리자 - 수강신청관리 페이지 이동");
		List<CourseVO> courses = service.getCourseList(cri);

		if (!courses.isEmpty()) {
			model.addAttribute("totalCount", courses.size());
			model.addAttribute("courses", courses);
		} else {
			model.addAttribute("listCheck", "empty");
		}

		// 전체 강좌 수와 페이지 DTO 계산
		int total = service.getCourseTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping(value = "adminInfo")
	public void adminInfoGET() throws Exception {

		logger.info("관리자 - 정보수정페이지 이동");

	}

	@GetMapping(value = "sms")
	public void adminSmsGET(Criteria cri, Model model) throws Exception {

		logger.info("관리자 - 문자관리페이지 이동");
		List<SmsVO> sms = service.getSmsList(cri);

		if (!sms.isEmpty()) {
			model.addAttribute("totalCount", sms.size());
			model.addAttribute("sms", sms);
		} else {
			model.addAttribute("listCheck", "empty");
		}

		// 전체 회원 수와 페이지 DTO 계산
		int total = service.getSmsTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);

	}

	@PostMapping(value = "sendSms.do")
	public String sendSmsPost(HttpServletRequest request) throws Exception {

		// SMS 전송을 위한 파라미터 설정
		HashMap<String, String> set = new HashMap<>();
		set.put("to", request.getParameter("recipientNumber")); // 수신번호
		set.put("from", request.getParameter("senderNumber")); // 발신번호
		set.put("text", request.getParameter("smsContent")); // 문자 내용
		set.put("type", "sms"); // 문자 타입
		set.put("app_version", "test app 1.2"); // 애플리케이션 버전

		System.out.println(set);

		try {
			// API key와 Secret Key 적용하여 Message 객체 생성
			Message coolsms = new Message(SMSapiKey, SMSapiSecret);
			// SMS 전송 및 결과 받기
			JSONObject result = coolsms.send(set);
			System.out.println(result.toString());
		} catch (CoolsmsException e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Code: " + e.getCode());
		}

		// SMS 전송 후 응답 페이지로 이동
		return "admin/sms"; // SMS 전송 결과 페이지
	}

	@GetMapping("/chat")
	public void adminChatGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 채팅상담관리페이지 이동");
		List<ChatVO> chat = service.getChatList(cri);

		if (!chat.isEmpty()) {
			model.addAttribute("totalCount", chat.size());
			model.addAttribute("chat", chat);
		} else {
			model.addAttribute("listCheck", "empty");
		}

		// 전체 회원 수와 페이지 DTO 계산
		int total = service.getChatTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping(value = "login")
	public void adminLoginGET() throws Exception {
		logger.info("로그인페이지 이동");
	}

}