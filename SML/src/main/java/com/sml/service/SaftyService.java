package com.sml.service;


import java.util.List;

import com.sml.model.Criteria;
import com.sml.model.NoticeVO;
import com.sml.model.SaftyVO;

public interface SaftyService {

	/* 생활안전 등록 */
	public void saftyRegister(SaftyVO safty) throws Exception; 
	
	/* 생활안전 조회 */
	public List<SaftyVO> saftyGetList(Criteria cri) throws Exception;
	
	/* 페이징 */
	public int saftyGetTotal(Criteria cri) throws Exception;
	
	/* 공지사항 상세 조회 */
	public SaftyVO saftyGetDetail(int saftyCode) throws Exception;
	
	/* 공지사항 조회수증가 */
	public int saftyCount(int saftyCode) throws Exception;
    
	/* 공지사항 좋아요 */
    boolean toggleLike(int saftyCode, int memCode);
    int getSaftyLikeCount(int saftyCode);
	
    /* 공지사항 수정 */
	public int saftyModify(SaftyVO saftyvo) throws Exception;
	
	/* 공지사항 삭제 */
	public int saftyDelete(int saftyCode);
	
}
