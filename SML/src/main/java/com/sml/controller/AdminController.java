package com.sml.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

@Controller
@RequestMapping("/admin")
@EnableScheduling
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService service;

	// 관리자 메인 페이지 이동
	@GetMapping("/main")
	public void adminMainGET(Model model) throws Exception {
		logger.info("관리자 페이지 이동");

		// 회원 수 및 연령대별 회원 수 조회 후 모델에 추가
		int memberCnt = service.getMemberCnt();
		model.addAttribute("memberCnt", memberCnt);

		Map<String, Integer> ageGroupCnt = service.getAgeGroupCnt();
		model.addAttribute("ageGroupCnt", ageGroupCnt);

		// 현재 연도 기준으로 월별 연령대별 회원 수 차트 데이터 추가
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		Map<String, int[]> chartData = service.getAgeGroupCountsByMonth(year);
		model.addAttribute("chartData", chartData);
	}

	// 특정 연도에 대한 월별 연령대별 회원 수 데이터를 비동기적으로 조회
	@GetMapping("/getDataForYear")
	@ResponseBody
	public Map<String, int[]> getDataForYear(@RequestParam("year") String year) throws Exception {
		logger.info("선택 년도 : " + year);
		return service.getAgeGroupCountsByMonth(year);
	}

	// 관리자 - 회원 관리 페이지 이동
	@GetMapping("/members")
	public void adminMembersGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 회원 관리 페이지 이동");

		// 페이징 처리된 회원 목록 조회 후 모델에 추가
		List<MemberVO> members = service.getMemberList(cri);
		model.addAttribute("members", members.isEmpty() ? "empty" : members);
		model.addAttribute("totalCount", service.getMemberTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getMemberTotal(cri)));
	}

	// 관리자 권한 업데이트
	@PostMapping("/updateAdm")
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

	// 회원 상태 업데이트
	@PostMapping("/updateStatus")
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

	// 관리자 - 수강 신청 관리 페이지 이동
	@GetMapping("/courses")
	public void adminCoursesGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 수강 신청 관리 페이지 이동");

		// 페이징 처리된 수강 신청 목록 조회 후 모델에 추가
		List<CourseVO> courses = service.getCourseList(cri);
		model.addAttribute("courses", courses.isEmpty() ? "empty" : courses);
		model.addAttribute("totalCount", service.getCourseTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getCourseTotal(cri)));
	}

	// 관리자 정보 수정 페이지 이동
	@GetMapping("/adminInfo")
	public void adminInfoGET() throws Exception {
		logger.info("관리자 - 정보 수정 페이지 이동");
	}

	// 관리자 - 문자 관리 페이지 이동
	@GetMapping("/sms")
	public void adminSmsGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 문자 관리 페이지 이동");

		// 페이징 처리된 SMS 목록 조회 후 모델에 추가
		List<SmsVO> sms = service.getSmsList(cri);
		model.addAttribute("sms", sms.isEmpty() ? "empty" : sms);
		model.addAttribute("totalCount", service.getSmsTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getSmsTotal(cri)));
	}

	// 회원 검색
	@GetMapping("/searchMember.do")
	@ResponseBody
	public List<MemberVO> searchMember(Criteria cri) throws Exception {
		logger.info("회원 검색 - 타입: " + cri.getType() + ", 키워드: " + cri.getKeyword());

		return service.getMemberList(cri);
	}

	// SMS 발송 및 DB 저장
	@PostMapping("/sendSms.do")
	public String sendSmsPost(HttpServletRequest request) throws Exception {
		// 파라미터로 전달받은 SMS 정보를 HashMap으로 저장
		HashMap<String, String> smsData = new HashMap<>();
		smsData.put("to", request.getParameter("recipientNumber")); // 수신번호
		smsData.put("from", request.getParameter("senderNumber")); // 발신번호
		smsData.put("text", request.getParameter("smsContent")); // 문자 내용
		smsData.put("type", "sms"); // 문자 타입
		smsData.put("app_version", "test app 1.2"); // 애플리케이션 버전
		smsData.put("memCode", request.getParameter("memCode")); // 회원 코드

		// 서비스에 HashMap을 넘겨서 SMS 발송 로직 처리
		service.sendSms(smsData);

		// SMS 전송 후 응답 페이지로 이동
		return "redirect:/admin/sms";
	}

	// 3일 연속 미출석 회원에게 안부 문자 발송 (매일 10:00 AM)
	@Scheduled(cron = "0 0 10 * * ?")
	public void sendReminderSmsToAbsentMembers() {
		logger.info("3일 연속 미출석 안부문자 발송일시 : {}", new Date());
		try {
			List<MemberVO> absentMembers = service.getAbsentMembers();
			logger.info("3일 연속 미출석 회원 수 : {}", absentMembers.size());
			if (!absentMembers.isEmpty()) {
				service.sendReminderSms(absentMembers);
			}
		} catch (Exception e) {
			logger.error("안부문자 발송 실패 : ", e);
		}
	}

	// 관리자 - 채팅 상담 관리 페이지 이동
	@GetMapping("/chat")
	public void adminChatGET(Criteria cri, Model model) throws Exception {
		logger.info("관리자 - 채팅 상담 관리 페이지 이동");

		// 페이징 처리된 채팅 목록 조회 후 모델에 추가
		List<ChatVO> chat = service.getChatList(cri);
		model.addAttribute("chat", chat.isEmpty() ? "empty" : chat);
		model.addAttribute("totalCount", service.getChatTotal(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getChatTotal(cri)));
	}

	// 채팅 내용 저장
	@PostMapping("/saveChatContent")
	public String saveChatContent(ChatVO chatVO, RedirectAttributes rttr) {
		logger.info("채팅 내용 저장 : " + chatVO);

		try {
			service.saveChatContent(chatVO);
			rttr.addFlashAttribute("result", "success");
		} catch (Exception e) {
			logger.error("채팅 저장 실패 : ", e);
			rttr.addFlashAttribute("result", "fail");
		}

		return "redirect:/admin/chat";
	}
}
