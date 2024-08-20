package com.sml.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.AdminMapper;
import com.sml.model.ChatVO;
import com.sml.model.CourseVO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.SmsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminMapper adminMapper;

	@Override
	public int memberGetTotal(Criteria cri) {
		return adminMapper.memberGetTotal(cri);
	}

	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		return adminMapper.getMemberList(cri);
	}

	@Override
	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception {
		adminMapper.updateAdm(memCode, memAdminCheck);
	}

	@Override
	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception {
		adminMapper.updateStatus(memCode, memStatus);
	}

	@Override
	public List<CourseVO> getCourseList(Criteria cri) {
		return adminMapper.getCourseList(cri);

	}

	@Override
	public int getCourseTotal(Criteria cri) {
		return adminMapper.getCourseTotal(cri);
	}

	@Override
	public List<SmsVO> getSmsList(Criteria cri) {
		return adminMapper.getSmsList(cri);

	}

	@Override
	public int getSmsTotal(Criteria cri) {
		return adminMapper.getSmsTotal(cri);
	}

	@Override
	public List<ChatVO> getChatList(Criteria cri) {
		return adminMapper.getChatList(cri);

	}

	@Override
	public int getChatTotal(Criteria cri) {
		return adminMapper.getChatTotal(cri);
	}

	@Override
	public void saveChatContent(ChatVO chatVO) {
		adminMapper.saveChatContent(chatVO);
	}

	@Override
	public int getMemberCnt() throws Exception {
		return adminMapper.getMemberCnt();
	}

	@Override
	public Map<String, Integer> getAgeGroupCnt() throws Exception {
		return adminMapper.getAgeGroupCnt();
	}

	@Override
	public Map<String, int[]> getAgeGroupCountsByMonth(String year) {
		List<Map<String, Object>> result = adminMapper.getAgeGroupCountsByMonth(year);
		Map<String, int[]> chartData = new HashMap<>();

		// 배열 초기화
		int[] under50 = new int[12];
		int[] age50s = new int[12];
		int[] age60s = new int[12];
		int[] age70s = new int[12];
		int[] age80s = new int[12];
		int[] age90plus = new int[12];

		// 결과가 null이 아니고 비어 있지 않을 경우에만 처리
		if (result != null && !result.isEmpty()) {
			for (Map<String, Object> row : result) {
				if (row != null) {
					Integer monthIndex = ((Number) row.get("MONTH")).intValue() - 1;

					if (monthIndex >= 0 && monthIndex < 12) {
						under50[monthIndex] = (row.get("UNDER50") != null) ? ((Number) row.get("UNDER50")).intValue()
								: 0;
						age50s[monthIndex] = (row.get("AGE50S") != null) ? ((Number) row.get("AGE50S")).intValue() : 0;
						age60s[monthIndex] = (row.get("AGE60S") != null) ? ((Number) row.get("AGE60S")).intValue() : 0;
						age70s[monthIndex] = (row.get("AGE70S") != null) ? ((Number) row.get("AGE70S")).intValue() : 0;
						age80s[monthIndex] = (row.get("AGE80S") != null) ? ((Number) row.get("AGE80S")).intValue() : 0;
						age90plus[monthIndex] = (row.get("AGE90PLUS") != null)
								? ((Number) row.get("AGE90PLUS")).intValue()
								: 0;
					}
				}
			}
		}

		// 차트 데이터에 배열 추가
		chartData.put("under50", under50);
		chartData.put("age50s", age50s);
		chartData.put("age60s", age60s);
		chartData.put("age70s", age70s);
		chartData.put("age80s", age80s);
		chartData.put("age90plus", age90plus);

		// 콘솔에 로그 출력 (디버깅용)
		for (Map.Entry<String, int[]> entry : chartData.entrySet()) {
			String ageGroup = entry.getKey();
			int[] monthlyCounts = entry.getValue();
			logger.info("Age Group: {} - Monthly Counts: {}", ageGroup, Arrays.toString(monthlyCounts));
		}

		return chartData;
	}

	@Override
	public void insertSms(SmsVO sms) {
		adminMapper.insertSms(sms);
	}

}
