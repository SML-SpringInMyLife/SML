package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.mapper.MemberMapper;
import com.sml.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
//	public void memberJoin() throws Exception{
//		MemberVO member = new MemberVO();
//		member.setMemberId("test");
//		member.setMemberPw("test");
//		member.setMemberName("test");
//		member.setMemberMail("test");
//		member.setMemberAddr1("test");
//		member.setMemberAddr2("test");
//		member.setMemberAddr3("test");
//		memberMapper.memberJoin(member);
//	}
	
//	@Test
//	public void memberIdChk() throws Exception{
//		String id = "lbi5320";
//		String id2 = "test";
//		
//		memberMapper.idCheck(id);
//		memberMapper.idCheck(id2);
//	}
	
	/* 로그인 쿼리 mapper 메서드 테스트 */
    @Test
    public void memberLogin() throws Exception{
        
        MemberVO member = new MemberVO();    // MemberVO 변수 선언 및 초기화
        
        /* 올바른 아이디 비번 입력경우 */
        member.setMemberId("testdd1");
        member.setMemberPw("test1");
        
        /* 올바른 않은 아이디 비번 입력경우 */
        //member.setMemberId("test1123");
        //member.setMemberPw("test1321321");
        
        memberMapper.memberLogin(member);
        System.out.println("결과 값 : " + memberMapper.memberLogin(member));
        
    }

}
