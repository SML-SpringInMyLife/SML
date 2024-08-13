package com.sml.service;

import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeService {
    
	/* 공지사항 등록 */
	public void noticeRegister(NoticeVO notice) throws Exception;                             //파일을 여러게 등록할 수 있으므로 list사용
	// public void noticeRegisterfile(NoticeVO notice, List<FileupVO> fileup) throws Exception;

	/* 공지사항 조회 */
	public List<NoticeVO> noticeGetList(Criteria cri) throws Exception;
	
	/* 페이징 */
	public int noticeGetTotal(Criteria cri) throws Exception;
	
	/* 공지사항 상세 조회 */
	public NoticeVO noticeGetDetail(int noticeCode) throws Exception;
	
	/* 공지사항 수정 */
	public int noticeModify(NoticeVO noticevo) throws Exception;
	
	/* 공지사항 삭제 */
	public int noticeDelete(int noticeCode);
}
