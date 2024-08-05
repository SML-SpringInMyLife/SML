package com.sml.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sml.model.MemberVO;
import com.sml.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
  //MemberService�� MemberController�� �ڵ����Եǵ��� �ڵ��߰�
    @Autowired
    private MemberService memberService;
    
  //ȸ������ ������ �̵�  		
  	@GetMapping("join")
  	public void joinGET() {
  		logger.info("ȸ������ ������ ����");
  	}		
  	
  //�α��� ������ �̵�
  	@GetMapping("login")
  	public void loginGET() {
  		logger.info("�α��� ������ ����");
  	}
  	
  //ȸ������
  	@PostMapping("/join")
  	public String joinPOST(MemberVO member) throws Exception{
  		
  		logger.info("ȸ������ ����");
  		
  		//ȸ������ ���� ����
  		memberService.MemberJoin(member);
  		
  		logger.info("�α��� ���� ����");
  		
  		return "redirect:/";
  	}

  	/* �α��� */
    @RequestMapping("/login")
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        System.out.println("login �޼��� ����");
        System.out.println("���޵� ������ : " + member);
    	
    	 HttpSession session = request.getSession();
    	 
    	 MemberVO lvo = memberService.memberLogin(member);
    	 
    	 System.out.println("111 : " + lvo);
    	 
    	 if(lvo == null) {                    // ��ġ���� �ʴ� ���̵�, ��й�ȣ �Է� ���
             
             int result = 0;
             rttr.addFlashAttribute("result", result);
             return "redirect:/member/login";             
         }
         
         session.setAttribute("member", lvo);    // ��ġ�ϴ� ���̵�, ��й�ȣ ��� (�α��� ����)
         
         return "redirect:/";        
        
    }
	
}