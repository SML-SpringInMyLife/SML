package com.sml.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.NoticeVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeMapperTest {

	@Autowired 
	private NoticeMapper mapper;
	
	@Test
	public void communityDetail() {
		
		int noticeCode = 4;
		NoticeVO notice = mapper.noticeGetDetail(noticeCode);
		System.out.println("notice........." +notice);
		
	}
	
}
