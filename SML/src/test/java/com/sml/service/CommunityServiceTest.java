package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CommunityVO;
import com.sml.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityServiceTest {
	
	@Autowired
	private CommunityService service;
	
	@Test
	public void communityEnrollTest() throws Exception{
		
		MemberVO member = new MemberVO();
	    member.setMemCode(5);
		
		CommunityVO community = new CommunityVO();
		community.setCommTitle("serviceTest");
		community.setCommContent("serviceTest");
		community.setCommWriter(member);
		
		service.communityEnroll(community);
		
	}
	
//	@Test
//	public String getBoardListTest() throws Exception {
//		List<CommunityVO> list = service.getBoardList();
//		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println("list" +i +"......" +list.get(i));
//		}
//		
//		return getBoardList;
//	}

}
