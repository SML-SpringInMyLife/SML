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
	public List<NoticeVO> noticeGetList(Criteria cri);
	
	/* 공지사항 페이징 */
	public int noticeGetTotal(Criteria cri);
	
	/* 공지사항 상세조회 */
	public NoticeVO noticeGetDetail(int noticeCode);
	
	/* 공지사항 수정 */
	public int noticeModify(NoticeVO noticevo);
	
	/* 공지사항 삭제 */
	public int noticeDelete(int noticeCode);
}
