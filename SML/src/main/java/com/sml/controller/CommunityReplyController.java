package com.sml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sml.model.CommunityReplyVO;
import com.sml.service.CommunityReplyService;

@RestController
public class CommunityReplyController {
	
	@Autowired
	private CommunityReplyService replyService;
	
	@PostMapping("/reply/enroll")
	public void enrollReplyPOST(CommunityReplyVO reply) {
		replyService.enrollReply(reply);
	}
	
	@PostMapping("/reply/check")
	public String replyCheckPOST(CommunityReplyVO reply) {
		return replyService.checkReply(reply);
	}

}
