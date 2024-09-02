package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.CommunityReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class CommunityReplyMapperTest {
	
	@Autowired
	private CommunityReplyMapper mapper;
	
	@Test
	public void enrollReplyTest() {
		
		int commCode = 24;
		int repWriter = 3;
		String repContent = "replyTest";
		
		CommunityReplyVO reply = new CommunityReplyVO();
		reply.setCommCode(commCode);
		reply.setRepWriter(repWriter);
		reply.setRepContent(repContent);
		
		mapper.enrollReply(reply);
	}

}
