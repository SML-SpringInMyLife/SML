package com.sml.mapper;

import java.util.List;

import com.sml.model.CommunityVO;
import com.sml.model.Criteria;

public interface CommunityMapper {
	
	public List<CommunityVO> getBoardList();
	
	public void communityEnroll (CommunityVO community);
	
	public CommunityVO communityDetail (int commCode);
	
	// modify
	
	// delete
	
	// paging
	
	// search
	
	// ��ȸ��
	
}
