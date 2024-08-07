package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CommunityMapper;
import com.sml.mapper.CommunityReplyMapper;
import com.sml.model.CommunityVO;
import com.sml.model.Criteria;
import com.sml.model.ReplyDTO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	CommunityMapper mapper;

	@Override
	public void communityEnroll(CommunityVO community) throws Exception {
		mapper.communityEnroll(community);
	}

	@Override
	public List<CommunityVO> getBoardList(Criteria cri) throws Exception {
		log.info("(service)getBoardList()......." +cri);
		return mapper.getBoardList(cri);
	}
	@Override
	public int communityGetTotal(Criteria cri) throws Exception {
		log.info("(service)communityGetTotal()......" +cri);
		return mapper.communityGetTotal(cri);
	}

	@Override
	public CommunityVO communityDetail(int commCode) throws Exception {
		log.info("상세 페이지 접속......" +commCode);
		return mapper.communityDetail(commCode);
	}

	@Override
	public int communityModify(CommunityVO community) throws Exception {
		log.info("(service) modify....." +community);
		return mapper.communityModify(community);
	}

	@Override
	public int communityDelete(int commCode) {
		log.info("Delete......");
		return mapper.communityDelete(commCode);
	}

	// 댓글
	
	@Autowired
	private CommunityReplyMapper replyMapper;
	
	@Override
	public int enrollReply(ReplyDTO dto) {
		int result = replyMapper.enrollReply(dto);
		return result;
	}

	@Override
	public List<ReplyDTO> listReply() throws Exception {
		log.info("(service)replyList().......");
		return replyMapper.listReply();
	}

//	@Override
//	public int replyGetTotal(Criteria cri) throws Exception {
//		return replyMapper.replyGetTotal(cri);
//	}
//
//	@Override
//	public int replyModify(ReplyDTO dto) throws Exception {
//		return replyMapper.replyModify(dto);
//	}
//
//	@Override
//	public int replyDelete(int repCode) throws Exception {
//		return replyMapper.replyDelete(repCode);
//	}
	
}
