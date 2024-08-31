package com.sml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sml.model.MemberVO;

@Controller
public class SnsLoginController {
	//네이버 로그인 페이지 이동
  	/*
  	@RequestMapping("/naver")
    public String naver() {
        return "naverLogin";
    }*/
  	
  		
  	
  	@RequestMapping(value="naverSave", method=RequestMethod.POST)
    public @ResponseBody String naverSave(
    		@RequestParam("n_id") String n_id,
            @RequestParam("n_name") String n_name,
    		@RequestParam("n_email") String n_email,
    		@RequestParam("n_phone") String n_phone){
    System.out.println("##Naver Login Info##");
    System.out.println("Email: " + n_email);
    System.out.println("ID: " + n_id);
    System.out.println("Name: " + n_name);
    System.out.println("Phone: " + n_phone);
    
    MemberVO member = new MemberVO();
    member.setMemId(n_id);
    member.setMemName(n_name);
    member.setMemMail(n_email);
    member.setMemPhone(n_phone);
    
    
    System.out.println("zzzzz =" + member.getMemId());
 
    String result = "no";
    
    if(member!=null) {
        result = "ok";
    }
 
    return result;
    
    }

}
