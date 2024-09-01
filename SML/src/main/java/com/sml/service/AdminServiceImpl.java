package com.sml.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.mapper.AdminMapper;
import com.sml.model.ChatVO;
import com.sml.model.CourseApplyDTO;
import com.sml.model.Criteria;
import com.sml.model.MemberVO;
import com.sml.model.SmsVO;

import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.api.Message;

@Service
@Log4j
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminMapper adminMapper;

	@Value("${SMS_KEY}")
	private String SMSapiKey;

	@Value("${SMS_SecretKEY}")
	private String SMSapiSecret;

	// MEMBER 관련

	// 회원 총 수 조회
	@Override
	public int getMemberTotal(Criteria cri) {
		return adminMapper.getMemberTotal(cri);
	}

	// 페이징 처리된 회원 목록 조회
	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		return adminMapper.getMemberList(cri);
	}

	// 전체 회원 수 조회
	@Override
	public int getMemberCnt() throws Exception {
		return adminMapper.getMemberCnt();
	}

	// 연령대별 회원 수 조회
	@Override
	public Map<String, Integer> getAgeGroupCnt() throws Exception {
		return adminMapper.getAgeGroupCnt();
	}

	// 월별 연령대별 회원 수 조회
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

		// 디버깅용 로그 출력
		for (Map.Entry<String, int[]> entry : chartData.entrySet()) {
			String ageGroup = entry.getKey();
			int[] monthlyCounts = entry.getValue();
			logger.info("Age Group: {} - Monthly Counts: {}", ageGroup, Arrays.toString(monthlyCounts));
		}

		return chartData;
	}

	// 관리자 권한 업데이트
	@Override
	public void updateAdm(@Param("memCode") int memCode, @Param("memAdminCheck") int memAdminCheck) throws Exception {
		adminMapper.updateAdm(memCode, memAdminCheck);
	}

	// 회원 상태 업데이트
	@Override
	public void updateStatus(@Param("memCode") int memCode, @Param("memStatus") int memStatus) throws Exception {
		adminMapper.updateStatus(memCode, memStatus);
	}

	// COURSES 관련

	// 페이징 처리된 수강 목록 조회
	@Override
	public List<CourseApplyDTO> getCourseList(Criteria cri) {
		return adminMapper.getCourseList(cri);
	}

	// 수강 총 수 조회
	@Override
	public int getCourseTotal(Criteria cri) {
		return adminMapper.getCourseTotal(cri);
	}

	// SMS 관련

	// 페이징 처리된 SMS 목록 조회
	@Override
	public List<SmsVO> getSmsList(Criteria cri) {
		return adminMapper.getSmsList(cri);
	}

	// SMS 총 수 조회
	@Override
	public int getSmsTotal(Criteria cri) {
		return adminMapper.getSmsTotal(cri);
	}

	// SMS 정보 저장
	@Override
	public void insertSms(SmsVO sms) {
		adminMapper.insertSms(sms);
	}

	// 3일 연속 미출석 회원 조회
	@Override
	public List<MemberVO> getAbsentMembers() {
		return adminMapper.selectAbsentMembers();
	}

	@Override
	public void sendSms(HashMap<String, String> set) throws Exception {
		// 테스트 모드 플래그 (true일 경우 실제 전송하지 않음)
		boolean isTestMode = true;

		logger.info("발송 문자 : {}", set);

		try {
			// 실제 API 전송 로직은 테스트 모드에서 제외
			if (!isTestMode) {
				Message coolsms = new Message(SMSapiKey, SMSapiSecret);
				JSONObject result = coolsms.send(set);
				logger.info("문자 발송 결과 : {}", result.toString());
			} else {
				logger.info("테스트 발송 - 실제 발송 안됨");
			}

			// 전송 후, SMS 정보를 DB에 저장
			SmsVO sms = new SmsVO();
			sms.setSmsContent(set.get("text"));
			sms.setSendDate(new Date()); // 현재 날짜 및 시간 설정
			sms.setStatus(isTestMode ? 0 : 1); // 0: 테스트 모드, 1: 발송 완료 상태
			sms.setMemCode(Integer.parseInt(set.get("memCode"))); // memCode 설정

			// SMS 정보 저장
			logger.info(sms.toString());
			adminMapper.insertSms(sms);

		} catch (Exception e) {
			logger.error("SMS 전송 오류 : ", e);
		}
	}

	// 미출석 회원에게 안부 문자 발송
	@Override
	@Transactional
	public void sendReminderSms(List<MemberVO> members) {
		logger.info(null, members.size());
		if (members == null || members.isEmpty()) {
			logger.warn("조회된 미출석 회원 없음");
			return;
		}

		// 테스트 모드 플래그 (true일 경우 실제 전송하지 않음)
		boolean isTestMode = true;

		for (MemberVO member : members) {
			String phone = member.getMemPhone();
			String memName = member.getMemName();
			if (phone == null || phone.trim().isEmpty()) {
				logger.warn("전화번호 없는 회원 스킵 : {}", member);
				continue; // null이거나 빈 전화번호를 가진 객체는 건너뜀
			}

			HashMap<String, String> set = new HashMap<>();
			set.put("to", phone);
			set.put("from", "01091933200");
			set.put("text", "[SML] 안녕하세요, " + memName + "님, 출석 체크를 잊지 마세요!");
			set.put("type", "sms");
			set.put("app_version", "test app 1.2");

			logger.info("발송 문자 : {}", set);

			try {
				// 실제 API 전송 로직은 테스트 모드에서 제외
				if (!isTestMode) {
					Message coolsms = new Message(SMSapiKey, SMSapiSecret);
					JSONObject result = coolsms.send(set);
					logger.info("문자 발송 결과 : {}", result.toString());
				} else {
					logger.info("테스트 발송 - 실제 발송 안됨");
				}

				logger.info(set.get("text"));
				// 전송 후, SMS 정보를 DB에 저장
				SmsVO sms = new SmsVO();
				sms.setSmsContent(set.get("text"));
				sms.setSendDate(new Date()); // 현재 날짜 및 시간 설정
				sms.setStatus(isTestMode ? 0 : 1); // 0: 테스트 모드, 1: 발송 완료 상태
				sms.setMemCode(member.getMemCode()); // memCode 설정

				// SMS 정보 저장
				logger.info(sms.toString());
				adminMapper.insertSms(sms);

			} catch (Exception e) {
				logger.error("SMS 전송 오류 : ", e);
			}
		}
	}

	// CHAT 관련

	// 페이징 처리된 채팅 목록 조회
	@Override
	public List<ChatVO> getChatList(Criteria cri) {
		return adminMapper.getChatList(cri);
	}

	// 채팅 총 수 조회
	@Override
	public int getChatTotal(Criteria cri) {
		return adminMapper.getChatTotal(cri);
	}

	// 채팅 내용 저장
	@Override
	public void saveChatContent(ChatVO chat) {
		adminMapper.saveChatContent(chat);
	}
}
