package com.sml.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeService {
    
	/* 공지사항 등록 */
	public void noticeRegister(NoticeVO notice) throws Exception;                  

	/* 공지사항 조회 */
	public List<NoticeVO> noticeGetList(Criteria cri) throws Exception;
	
	/* 페이징 */
	public int noticeGetTotal(Criteria cri) throws Exception;
	
	/* 공지사항 상세 조회 */
	public NoticeVO noticeGetDetail(int noticeCode) throws Exception;
	
	/* 공지사항 조회수증가 */
	public int noticeCount(int noticeCode) throws Exception;
	
	/* 좋아요 기능 */
    boolean noticeLike(int noticeCode) throws Exception;
	
	/* 공지사항 수정 */
	public int noticeModify(NoticeVO noticevo) throws Exception;
	
	/* 공지사항 삭제 */
	public int noticeDelete(int noticeCode);
}
