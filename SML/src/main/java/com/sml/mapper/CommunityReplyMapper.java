package com.sml.mapper;

import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.ReplyDTO;

public interface CommunityReplyMapper {
	
	public int enrollReply(ReplyDTO dto);
	
	public List<ReplyDTO> listReply();

	public int replyGetTotal(Criteria cri);
	
	public int replyModify(ReplyDTO dto);
	
	public int replyDelete(int repCode);
}
