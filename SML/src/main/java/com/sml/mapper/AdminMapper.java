package com.sml.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.ChatVO;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.SmsVO;

public interface AdminMapper {

	public List<MemberVO> getMemberList(Criteria cri);

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck);

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus);

	public int getMemberCnt();

	public Map<String, Integer> getAgeGroupCnt();

	public List<Map<String, Object>> getAgeGroupCountsByMonth(String year);

	public int getMemberTotal(Criteria cri);

	public List<CourseVO> getCourseList(Criteria cri);

	public int getCourseTotal(Criteria cri);

	public List<SmsVO> getSmsList(Criteria cri);

	public int getSmsTotal(Criteria cri);

	public List<ChatVO> getChatList(Criteria cri);

	public int getChatTotal(Criteria cri);

	public void saveChatContent(ChatVO chatVO);

	public void insertSms(SmsVO sms);

}
