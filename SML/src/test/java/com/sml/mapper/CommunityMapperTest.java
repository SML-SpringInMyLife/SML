package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CommunityVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityMapperTest {
	
	@Autowired 
	private CommunityMapper mapper;
	
	@Test
	public void communityEnrollTest() throws Exception {
		
		CommunityVO community= new CommunityVO();
		
		community.setCommTitle("mapperTest");
		community.setCommContent("mapperTest");
		community.setMemberCode(4);
		
		mapper.communityEnroll(community);
		
	}
	
//	@Test
//	public void getBoardListTest() throws Exception {
//		List<CommunityVO> list = mapper.boardList();
//		
//		for(int i=0; i<list.size(); i++) {
//			System.out.println("list" +i +"......" +list.get(i));
//		}
//	}

}
