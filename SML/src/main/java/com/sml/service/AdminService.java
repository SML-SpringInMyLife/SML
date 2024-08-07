package com.sml.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.MemberVO;

public interface AdminService {

	public List<MemberVO> getMemberList() throws Exception;

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception;

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception;

	public int getMemberCnt() throws Exception;;

	public Map<String, Integer> getAgeGroupCnt() throws Exception;

	public Map<String, int[]> getAgeGroupCountsByMonth(String year) throws Exception ;


}
