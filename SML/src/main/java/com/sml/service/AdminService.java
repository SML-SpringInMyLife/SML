package com.sml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sml.model.ChatVO;
import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.SmsVO;

public interface AdminService {

	// MEMBER 관련

	public List<MemberVO> getMemberList(Criteria cri) throws Exception;

	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception;

	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception;

	public int getMemberCnt() throws Exception;;

	public Map<String, Integer> getAgeGroupCnt() throws Exception;

	public Map<String, int[]> getAgeGroupCountsByMonth(String year) throws Exception;

	public int getMemberTotal(Criteria cri);

	// COURSES 관련

	public List<CourseApplyDTO> getCourseList(Criteria cri);

	public int getCourseTotal(Criteria cri);

	// SMS 관련

	public List<SmsVO> getSmsList(Criteria cri);

	public int getSmsTotal(Criteria cri);

	public void sendSms(HashMap<String, String> set) throws Exception;

	public void insertSms(SmsVO sms);

	public List<MemberVO> getAbsentMembers();

	public void sendReminderSms(List<MemberVO> absentMembers) throws Exception;

	// CHAT 관련

	public List<ChatVO> getChatList(Criteria cri);

	public int getChatTotal(Criteria cri);

	public void saveChatContent(ChatVO chat);

}
