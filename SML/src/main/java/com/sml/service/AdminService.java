package com.sml.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.ChatVO;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.SmsVO;

public interface AdminService {

	public List<MemberVO> getMemberList(Criteria cri) throws Exception;

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception;

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception;

	public int getMemberCnt() throws Exception;;

	public Map<String, Integer> getAgeGroupCnt() throws Exception;

	public Map<String, int[]> getAgeGroupCountsByMonth(String year) throws Exception ;

	public int memberGetTotal(Criteria cri);

	public void saveChatContent(ChatVO chatVO);

	public List<CourseVO> getCourseList(Criteria cri);

	public int getCourseTotal(Criteria cri);

	public List<SmsVO> getSmsList(Criteria cri);

	public int getSmsTotal(Criteria cri);

	public List<ChatVO> getChatList(Criteria cri);

	public int getChatTotal(Criteria cri);

}
