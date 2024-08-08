package com.sml.mapper;

import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeMapper {
	/* 공지사항 등록 */
	public void noticeRegister(NoticeVO notice);
	
	/* public void fileUp(FileupVO file); //파일 업로드 */
	
	/* 공지사항 조회(목록) */
	public List<NoticeVO> noticeGetList();
}
