package com.sml.mapper;

import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeMapper {
	/* 공지사항 등록 */
	public int noticeRegister(NoticeVO notice);
	
	/* 이미지 등록 */
	public void imageEnroll(FileupVO vo);
	
	/* 공지사항 조회(목록) */
	public List<NoticeVO> noticeGetList(Criteria cri);
	
	/* 공지사항 페이징 */
	public int noticeGetTotal(Criteria cri);
	
	/* 공지사항 상세조회 */
	public NoticeVO noticeGetDetail(int noticeCode);
	
	/* 공지사항 조회수 증가 기능 */
	public int noticeCount(int noticeCode); 
	
	/* 공지사항 좋아요 */
	public int noticeLike(int noticeCode);
	
	/* 공지사항 수정 */
	public int noticeModify(NoticeVO noticevo);
	
	/* 공지사항 삭제 */
	public int noticeDelete(int noticeCode);
	
	
}
