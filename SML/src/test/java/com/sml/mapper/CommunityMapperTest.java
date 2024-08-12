package com.sml.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.controller.CommunityController;
import com.sml.model.CommunityVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityMapperTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired 
	private CommunityMapper mapper;
	
//	@Test
//	public void communityEnrollTest() throws Exception {
//
//		MemberVO member = new MemberVO();
//	    member.setMemCode(5); 
//	    
//	    CommunityVO community= new CommunityVO();
//		community.setCommTitle("mapperTest");
//		community.setCommContent("mapperTest");
//		community.setCommWriter(member);
//		
//		mapper.communityEnroll(community);
//		
//	}
	
//	@Test
//	public void getBoardListTest() throws Exception {
//
//		Criteria cri = new Criteria(3, 10);
//		cri.setKeyword("mapperTest");
//		
//		List<CommunityVO> list = mapper.getBoardList(cri);
//		logger.info("mapper test 실행");
//		
//		for(int i=0; i<list.size(); i++) {
//			//System.out.println("list" +i +"......" +list.get(i));
//			logger.info("boardList test" +list.get(i) );
//		}
//	}
	
//	@Test
//	public void communityGetTotalTest() throws Exception{
//		
//		Criteria cri = new Criteria();
//		cri.setKeyword("mapperTest");
//		
//		int total = mapper.communityGetTotal(cri);
//		
//		System.out.println("total.........." +total);
//		
//	}
	
	@Test
	public void communityDetail() {
		
		int commCode = 7;
		CommunityVO community = mapper.communityDetail(commCode);
		logger.info("Detail........." +community);
		
	}
	
//	@Test
//	public void communityModifyTest() {
//		
//		CommunityVO community = new CommunityVO();
//		
//		community.setCommCode(7);
//		logger.info("수정 전....." +mapper.communityModify(community));
//		
//		community.setCommTitle("modifyMapperTest");
//		community.setCommContent("modifyMapperTest");
//		
//		mapper.communityModify(community);
//		logger.info("수정 후......" +mapper.communityModify(community));
//	}
	
	@Test
	public void communityDeleteTest() {
		int commCode = 7;
		int result = mapper.communityDelete(commCode);
		if (result == 1) {
			System.out.println("삭제 성공");
		}
	}

}
