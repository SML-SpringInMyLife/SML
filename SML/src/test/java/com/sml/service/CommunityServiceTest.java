package com.sml.service;

import org.apache.maven.doxia.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.controller.CommunityController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityService service;
	
//	@Test
//	public void communityEnrollTest() throws Exception{
//		
//		MemberVO member = new MemberVO();
//	    member.setMemCode(5);
//		
//		CommunityVO community = new CommunityVO();
//		community.setCommTitle("serviceTest");
//		community.setCommContent("serviceTest");
////		community.setCommWriter(member);
//		
//		service.communityEnroll(community);
//		
//	}
	
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
	
	@Test
	public void communityDetailTest() throws Exception{
		int commCode = 7;
		logger.info("Detail......" +service.communityDetail(commCode));
	}

}
