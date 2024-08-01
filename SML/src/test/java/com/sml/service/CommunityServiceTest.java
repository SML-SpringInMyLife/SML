package com.sml.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CommunityVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityServiceTest {
	
	@Autowired
	private CommunityService service;
	
	@Test
	public void communityEnrollTest() throws Exception{
		
		CommunityVO community = new CommunityVO();
		community.setCommTitle("serviceTest");
		community.setCommContent("serviceTest");
		community.setMemberCode(4);
		
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
