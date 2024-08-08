package com.sml.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.MemberVO;

public interface AdminMapper {

	public List<MemberVO> getMemberList();

	public List<MemberVO> getMemberList(@Param("category") String category, @Param("keyword") String keyword);

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck);

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus);

	public int getMemberCnt();

	public Map<String, Integer> getAgeGroupCnt();

	public List<Map<String, Object>> getAgeGroupCountsByMonth(String year);


}
