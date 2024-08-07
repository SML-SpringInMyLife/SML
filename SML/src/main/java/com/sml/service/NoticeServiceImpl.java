package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.NoticeMapper;
import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
  
	@Autowired 
	NoticeMapper noticeMapper;

	@Override  //게시글 등록
	public void noticeRegister(NoticeVO notice) throws Exception {
		
	    noticeMapper.noticeRegister(notice);
	}

	@Override  //게시글 조회
	public List<NoticeVO> noticeGetList() throws Exception {
		  
		return noticeMapper.noticeGetList();
	}
    
	
	/*
	@Override
	public void noticeRegisterfile(NoticeVO notice,List<FileupVO> fileup) throws Exception  {
		 // 공지사항 등록
        noticeMapper.noticeRegister(notice);

        // 생성된 NOTICE_CODE를 사용하여 파일 업로드
        for (FileupVO file : fileup) {
        	file.setNoticeCode(notice.getNoticeCode());
      
			noticeMapper.fileUp(file);
        }
		
	}

	*/
	
	
	
}
