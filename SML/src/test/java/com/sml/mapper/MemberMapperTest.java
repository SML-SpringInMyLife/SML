package com.sml.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	@Autowired
	private MemberMapper membermapper;
	
	

	/*
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemId("test");
		member.setMemPw("1234");
		member.setMemName("홍길동");
		member.setMemMail("user123@example.com");
		member.setMemPhone("010-1234-5678");
		member.setMemEmerPhone("010-9876-5432");
		member.setMemAddr1("서울시");
		member.setMemAddr2("강남구");
		member.setMemAddr3("테헤란로 123");
		member.setMemBirth(new Date()); 
		member.setMemJoinDate(new Date()); // 현재 날짜
		member.setMemQuitDate(new Date()); // 탈퇴하지 않은 상태
		member.setMemStatus(1); // 활성 상태라고 가정
		member.setMemAdminCheck(1);
		member.setMemTotalPoint(1000); // 초기 포인트		
		membermapper.memberJoin(member);
		
	}
	
	
	//로그인 메퍼 메서드 테스트
	@Test
	public void memberLogin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemId("test");
		member.setMemPw("1234");
		
		membermapper.memberLogin(member);
		System.out.println("결과" + membermapper.memberLogin(member));
	}
	
	
	//아이디 중복 검사
	@Test
	public void memberIdChk() throws Exception{
		String id = "test";
		String id2 = "test2";
		membermapper.idCheck(id);
		membermapper.idCheck(id2);	
	}
	*/

}
