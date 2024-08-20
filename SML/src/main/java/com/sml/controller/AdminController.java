package com.sml.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;
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

		// 회원 수 및 연령대별 회원 수를 조회하여 모델에 추가
		int memberCnt = service.getMemberCnt();
		model.addAttribute("memberCnt", memberCnt);

		Map<String, Integer> ageGroupCnt = service.getAgeGroupCnt();
		model.addAttribute("ageGroupCnt", ageGroupCnt);

		// 현재 연도를 기준으로 월별 연령대별 회원 수 차트 데이터 추가
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Map<String, int[]> chartData = service.getAgeGroupCountsByMonth(year);
		model.addAttribute("chartData", chartData);
	}

	/* 특정 연도에 대한 월별 연령대별 회원 수 데이터를 비동기적으로 조회 */
	@GetMapping(value = "getDataForYear")
	@ResponseBody
	public Map<String, int[]> getDataForYear(@RequestParam("year") String year) throws Exception {
		logger.info("선택 년도 : " + year);
		return service.getAgeGroupCountsByMonth(year);
	}

	/* 관리자 - 회원 관리 페이지 이동 */
	@GetMapping(value = "members")
	public void adminMembersGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 회원 관리 페이지 이동");

		// 페이징 처리된 회원 목록 가져오기
		List<MemberVO> members = service.getMemberList(cri);
		model.addAttribute("members", members.isEmpty() ? "empty" : members);
		model.addAttribute("totalCount", service.memberGetTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.memberGetTotal(cri)));
	}

	/* 관리자 권한 업데이트 */
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

	/* 회원 상태 업데이트 */
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

	/* 관리자 - 수강 신청 관리 페이지 이동 */
	@GetMapping(value = "courses")
	public void adminCoursesGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 수강 신청 관리 페이지 이동");

		List<CourseVO> courses = service.getCourseList(cri);
		model.addAttribute("courses", courses.isEmpty() ? "empty" : courses);
		model.addAttribute("totalCount", service.getCourseTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getCourseTotal(cri)));
	}

	/* 관리자 정보 수정 페이지 이동 */
	@GetMapping(value = "adminInfo")
	public void adminInfoGET() throws Exception {
		logger.info("관리자 - 정보 수정 페이지 이동");
	}

	/* 관리자 - 문자 관리 페이지 이동 */
	@GetMapping(value = "sms")
	public void adminSmsGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 문자 관리 페이지 이동");

		List<SmsVO> sms = service.getSmsList(cri);
		model.addAttribute("sms", sms.isEmpty() ? "empty" : sms);
		model.addAttribute("totalCount", service.getSmsTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getSmsTotal(cri)));
	}

	@GetMapping(value = "searchMember.do")
	@ResponseBody
	public List<MemberVO> searchMember(Criteria cri) throws Exception {
		logger.info("회원 검색 - 타입: " + cri.getType() + ", 키워드: " + cri.getKeyword());

		// getMemberList를 호출하여 검색된 회원 목록 반환
		return service.getMemberList(cri);
	}

	/* SMS 발송 및 DB 저장 + 테스트발송 모드 추가 */
	@PostMapping(value = "sendSms.do")
	@Transactional
	public String sendSmsPost(HttpServletRequest request) throws Exception {

		// 테스트 모드 플래그 (true일 경우 실제 전송하지 않음)
		boolean isTestMode = true; // 환경 변수에서 읽어오기

		// SMS 전송을 위한 파라미터 설정
		HashMap<String, String> set = new HashMap<>();
		set.put("to", request.getParameter("recipientNumber")); // 수신번호
		set.put("from", request.getParameter("senderNumber")); // 발신번호
		set.put("text", request.getParameter("smsContent")); // 문자 내용
		set.put("type", "sms"); // 문자 타입
		set.put("app_version", "test app 1.2"); // 애플리케이션 버전

		logger.info("Sending SMS: {}", set);

		try {
			// 실제 API 전송 로직은 테스트 모드에서 제외
			if (!isTestMode) {
				Message coolsms = new Message(SMSapiKey, SMSapiSecret);
				JSONObject result = coolsms.send(set);
				logger.info("SMS send result: {}", result.toString());
			} else {
				logger.info("Test mode enabled - SMS not sent");
			}

			logger.info(set.get("text"));
			// 전송 후, SMS 정보를 DB에 저장
			SmsVO sms = new SmsVO();
			sms.setSmsContent(set.get("text"));
			sms.setSendDate(new Date()); // 현재 날짜 및 시간 설정
			sms.setStatus(isTestMode ? 0 : 1); // 0: 테스트 모드, 1: 발송 완료 상태
			sms.setMemCode(Integer.parseInt(request.getParameter("memCode"))); // memCode 설정

				
			// SMS 정보 저장
			logger.info(sms.toString());
			service.insertSms(sms);

		} catch (Exception e) {
			logger.error("SMS 전송 오류 : ", e);
		}

		// SMS 전송 후 응답 페이지로 이동
		return "redirect:/admin/sms";
	}

	/* 관리자 - 채팅 상담 관리 페이지 이동 */
	@GetMapping("/chat")
	public void adminChatGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 채팅 상담 관리 페이지 이동");

		List<ChatVO> chat = service.getChatList(cri);
		model.addAttribute("chat", chat.isEmpty() ? "empty" : chat);
		model.addAttribute("totalCount", service.getChatTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getChatTotal(cri)));
	}

	/* 로그인 페이지 이동 */
	@GetMapping(value = "login")
	public void adminLoginGET() throws Exception {
		logger.info("로그인 페이지 이동");
	}
}
