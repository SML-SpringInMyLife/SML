package com.sml.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.ChatVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;

public interface AdminService {

	public List<MemberVO> getMemberList() throws Exception;

	public List<MemberVO> getMemberList(String category, String keyword);

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception;

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception;

	public int getMemberCnt() throws Exception;;

	public Map<String, Integer> getAgeGroupCnt() throws Exception;

	public Map<String, int[]> getAgeGroupCountsByMonth(String year) throws Exception ;

	public int memberGetTotal(Criteria cri);

	public void saveChatContent(ChatVO chatVO);

}
