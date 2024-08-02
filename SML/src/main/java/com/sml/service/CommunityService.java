package com.sml.service;

import java.util.List;

import com.sml.model.CommunityVO;

public interface CommunityService {
	
	public void communityEnroll(CommunityVO community)throws Exception;

	public List<CommunityVO> getBoardList()throws Exception;
	
	public CommunityVO communityDetail(int commCode) throws Exception;
	
	// modify
	
	// delete
	
	// paging
	
	// search
	

}
