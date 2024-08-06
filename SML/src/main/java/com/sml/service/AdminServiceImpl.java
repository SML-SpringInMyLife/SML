package com.sml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.AdminMapper;
import com.sml.model.MemberVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		return adminMapper.getMemberList();
	}

	@Override
	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception {
		adminMapper.updateStatus(memCode, memStatus);
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

		for (Map<String, Object> row : result) {
			String month = (String) row.get("month");
			Integer monthIndex = null;
			if (month != null) {
				try {
					monthIndex = Integer.parseInt(month) - 1;
				} catch (NumberFormatException e) {
					// month가 올바르지 않은 형식일 경우, 로그를 기록하거나 기본값을 사용
					monthIndex = -1; // 이 경우에는 예외를 피하기 위해 -1로 설정
				}
			}

			// monthIndex가 유효한지 확인
			if (monthIndex != null && monthIndex >= 0 && monthIndex < 12) {
				under50[monthIndex] = (row.get("under50") != null) ? ((Number) row.get("under50")).intValue() : 0;
				age50s[monthIndex] = (row.get("age50s") != null) ? ((Number) row.get("age50s")).intValue() : 0;
				age60s[monthIndex] = (row.get("age60s") != null) ? ((Number) row.get("age60s")).intValue() : 0;
				age70s[monthIndex] = (row.get("age70s") != null) ? ((Number) row.get("age70s")).intValue() : 0;
				age80s[monthIndex] = (row.get("age80s") != null) ? ((Number) row.get("age80s")).intValue() : 0;
				age90plus[monthIndex] = (row.get("age90plus") != null) ? ((Number) row.get("age90plus")).intValue() : 0;
			}
		}

		// 결과를 차트 데이터로 변환
		chartData.put("under50", under50);
		chartData.put("age50s", age50s);
		chartData.put("age60s", age60s);
		chartData.put("age70s", age70s);
		chartData.put("age80s", age80s);
		chartData.put("age90plus", age90plus);

		return chartData;
	}

}
