package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.MemberCheckVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MypageMapperTest {
	
	@Autowired
	private MypageMapper mypagemapper;
	
	@Test
	public void memberCheckTest() throws Exception{
		MemberCheckVO membercheck = new MemberCheckVO();

		membercheck.setCheckCode(1);  // int 타입으로 변경
	    membercheck.setCheckDate(java.sql.Date.valueOf("2024-08-13"));  
	}
	
	
	//@Test
	public void memberDailyCheck() throws Exception{
		MemberCheckVO membercheck = new MemberCheckVO();

		membercheck.setCheckCode(1);  // int 타입으로 변경
	    membercheck.setCheckDate(java.sql.Date.valueOf("2024-08-01"));  
		membercheck.setStatus(1);
		membercheck.setMemCode(3);
	
	
	}

}
