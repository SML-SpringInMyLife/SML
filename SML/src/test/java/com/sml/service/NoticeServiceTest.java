package com.sml.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeServiceTest {

	@Autowired
	private NoticeService service;
	
	/* 글 등록 & 이미지 등록 테스트 */
	@Test
	public void noticeEnrollTEsts() throws Exception {
		
	    NoticeVO notice = new NoticeVO();
	    
        notice.setMemCode(1); // 테스트용 회원 코드
        notice.setCategoryCode(1); // 테스트용 카테고리 코드
        notice.setNoticeTitle("테스트 공지사항");
        notice.setNoticeBody("이것은 테스트 공지사항의 내용입니다.");
        notice.setNoticeStatus("Y");
        
        // 이미지 리스트 생성
        List<FileupVO> imageList = new ArrayList<FileupVO>();
        
        FileupVO image1 = new FileupVO();
        image1.setFileName("테스트이미지1.jpg");
        image1.setFilePath("/path/to/image1");
        image1.setFileUuid("uuid-1234");
        image1.setFileType("NOTICE");
        imageList.add(image1);
        
        FileupVO image2 = new FileupVO();
        image2.setFileName("테스트이미지2.png");
        image2.setFilePath("/path/to/image2");
        image2.setFileUuid("uuid-5678");
        image2.setFileType("NOTICE");
        imageList.add(image2);
        
        imageList.add(image1);
		imageList.add(image2);
        
        notice.setImageList(imageList);
        
     // noticeRegister() 메서드 호출
     		service.noticeRegister(notice);
     		
     		System.out.println("등록된 VO : " + notice);
        
		
	}
	
}
