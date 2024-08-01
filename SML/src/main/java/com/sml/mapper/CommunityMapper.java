package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;

public interface CommunityMapper {
	
	public List<CommunityVO> getBoardList(Criteria cri);
	
	public void communityEnroll (CommunityVO community);
	
	// public CommunityVO boardDetail (int commCode);
	
	// modify
	
	// delete
	
	// paging
	
	// search
	
	// Á¶È¸¼ö
	
}
