package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.ReplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityReplyMapperTest {
	
	@Autowired
	private CommunityReplyMapper mapper;
	
	@Test
	public void enrollReplyTest() {
		
		int memCode = 5;
		int commCode = 70;
		String repContent = "replyTest";
		
		ReplyDTO dto = new ReplyDTO();
		dto.setMemCode(memCode);
		dto.setCommCode(commCode);
		dto.setRepContent(repContent);
		
		mapper.enrollReply(dto);
	}

}
