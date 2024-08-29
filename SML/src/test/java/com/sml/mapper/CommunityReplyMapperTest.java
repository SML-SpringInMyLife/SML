package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CommunityReplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityReplyMapperTest {
	
	@Autowired
	private CommunityReplyMapper mapper;
	
	@Test
	public void enrollReplyTest() {
		
		int commCode = 24;
		int memCode = 3;
		String repContent = "replyTest";
		
		CommunityReplyDTO dto = new CommunityReplyDTO();
		dto.setCommCode(commCode);
		dto.setMemCode(memCode);
		dto.setRepContent(repContent);
		
		mapper.enrollReply(dto);
	}

}
