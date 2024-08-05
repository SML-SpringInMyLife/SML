package com.sml.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	/* ������ ���� ������ �̵� */
	@GetMapping(value = "main")
	public void adminMainGET() throws Exception {

		logger.info("������ ������ �̵�");

	}

	@GetMapping(value = "courses")
	public void adminCoursesGET() throws Exception {

		logger.info("������ - ������û���� ������ �̵�");

	}

	@GetMapping(value = "members")
	public void adminMembersGET(Model model) throws Exception {

		logger.info("������ - ȸ������������ �̵�");

		List<MemberVO> list = service.getMemberList();
		if (!list.isEmpty()) {
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
		}

	}

	@GetMapping(value = "edit")
	public void adminEditGET() throws Exception {

		logger.info("������ - �������������� �̵�");

	}

	@GetMapping(value = "sms")
	public void adminSmsGET() throws Exception {

		logger.info("������ - ���ڰ��������� �̵�");

	}

	@PostMapping(value = "sms/sendSms.do")
	public String sendSms(HttpServletRequest request) throws Exception {

		// API Key�� Secret Key�� �Է� (Coolsms���� �߱޹��� ��)
		String api_key = "";
		String api_secret = "";
		Message coolsms = new Message(api_key, api_secret);

		// SMS ������ ���� �Ķ���� ����
		HashMap<String, String> set = new HashMap<>();
		set.put("to", request.getParameter("recipientNumber")); // ���Ź�ȣ
		set.put("from", request.getParameter("senderNumber")); // �߽Ź�ȣ
		set.put("text", request.getParameter("smsContent")); // ���� ����
		set.put("type", "sms"); // ���� Ÿ��
		set.put("app_version", "test app 1.2"); // ���ø����̼� ����

		System.out.println(set);

		try {
			// SMS ���� �� ��� �ޱ�
			JSONObject result = coolsms.send(set);
			System.out.println(result.toString());
		} catch (CoolsmsException e) {
			System.out.println("Error Message: " + e.getMessage());
			System.out.println("Error Code: " + e.getCode());
		}

		// SMS ���� �� ���� �������� �̵�
		return "admin/sms"; // SMS ���� ��� ������
	}

	@GetMapping(value = "chat")
	public void adminChatGET() throws Exception {

		logger.info("������ - ä�û����������� �̵�");

	}

	@GetMapping(value = "login")
	public void adminLoginGET() throws Exception {

		logger.info("�α��������� �̵�");

	}
}
