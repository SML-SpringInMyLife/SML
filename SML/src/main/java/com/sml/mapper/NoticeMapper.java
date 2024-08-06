package com.sml.mapper;

import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeMapper {
	/* 공지사항 등록 */
	public void noticeRegister(NoticeVO notice);
	public void fileUp(FileupVO file); //파일 업로드
	
}
