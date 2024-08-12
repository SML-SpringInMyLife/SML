package com.sml.service;


//import java.text.SimpleDateFormat;
//import java.time.LocalDate;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.MemberCheckVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MypageServiceTest {
	
	@Autowired
	private MypageService service;
	
	@Test
	public void testInsert() {		
		
		//오늘 날짜 가져오기
		Date today = new Date();
		/*
		//날짜 형식 지정
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		//문자열로 변환
		String sDateformat = dateformat.format(today);
		//Date 타입으로 변환
		Date toDate = java.sql.Date.valueOf(sDateformat);
		*/
		MemberCheckVO vo = new MemberCheckVO();
		vo.setCheckDate(today);		
		vo.setStatus(1);
		vo.setMemCode(1);
		
		service.insertMemberCheck(vo);
	}

}
