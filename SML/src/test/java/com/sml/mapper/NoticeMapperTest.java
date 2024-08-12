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
	
 /*	@Test
	public void communityDetail() {
		
		int noticeCode = 4;
		NoticeVO notice = mapper.noticeGetDetail(noticeCode);
		System.out.println("notice........." +notice);
		
	}
	*/
	
   @Test 
	public void noticeModifyTest() {
		NoticeVO notice = new NoticeVO();
		
	   	notice.setNoticeCode(4);
		System.out.println("수정전..............." + mapper.noticeGetDetail(notice.getNoticeCode()));
	  
		notice.setNoticeTitle("글제목 수정하였습니다.");
        notice.setNoticeBody("글수정 하였습니다 ");
         
        mapper.noticeModify(notice);
        System.out.println("수정후..........." + mapper.noticeGetDetail(notice.getNoticeCode()));
   }
	
}
