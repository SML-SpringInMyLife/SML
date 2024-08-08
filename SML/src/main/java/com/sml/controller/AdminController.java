package com.sml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.MemberVO;
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
		String year = "2024"; // 기본 연도
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
	public void adminMembersGET(Model model) throws Exception {

		logger.info("관리자 - 회원관리페이지 이동");
		List<MemberVO> members = service.getMemberList();

		if (!members.isEmpty()) {
			model.addAttribute("members", members);
			model.addAttribute("totalCount", members.size());
		} else {
			model.addAttribute("listCheck", "empty");
		}

	}

	// 멤버 검색
	@PostMapping(value = "members/search")
	public String getMemberList(@RequestParam String category, @RequestParam String keyword, Model model) {
		logger.info("검색 카테고리: " + category + ", 검색어: " + keyword);

		List<MemberVO> members = service.getMemberList(category, keyword);
		model.addAttribute("members", members);
		model.addAttribute("totalCount", members.size());
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);

		return "admin/members"; // 검색 결과를 포함한 뷰를 반환합니다.
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
	public void adminCoursesGET() throws Exception {

		logger.info("관리자 - 수강신청관리 페이지 이동");

	}

	@GetMapping(value = "edit")
	public void adminEditGET() throws Exception {

		logger.info("관리자 - 정보수정페이지 이동");

	}

	@GetMapping(value = "sms")
	public void adminSmsGET(Model model) throws Exception {

		logger.info("관리자 - 문자관리페이지 이동");
		List<MemberVO> members = service.getMemberList();

		if (!members.isEmpty()) {
			model.addAttribute("members", members);
		} else {
			model.addAttribute("listCheck", "empty");
		}

	}

	@PostMapping("sms/sendSearch")
	public ResponseEntity<List<MemberVO>> searchMembers(@RequestParam String category, @RequestParam String keyword) {
		logger.info("검색 카테고리: " + category + ", 검색어: " + keyword);

		// 회원 목록을 검색하는 서비스 호출
		List<MemberVO> members = service.getMemberList(category, keyword);

		// 클라이언트에 MemberVO 객체 리스트를 직접 반환
		return ResponseEntity.ok(members); // 검색 결과를 JSON으로 반환
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

	@GetMapping(value = "chat")
	public void adminChatGET() throws Exception {

		logger.info("관리자 - 채팅상담관리페이지 이동");

	}

	@GetMapping(value = "login")
	public void adminLoginGET() throws Exception {

		logger.info("로그인페이지 이동");

	}
}