package com.sml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sml.model.CommunityReplyDTO;
import com.sml.service.CommunityService;

@RestController
//@RequestMapping("/reply")
public class CommunityReplyController {
	
	@Autowired
	private CommunityService replyService;
	
	@PostMapping("/enroll")
	public void enrollReplyPOST(CommunityReplyDTO dto) {
		replyService.enrollReply(dto);
	}

}
