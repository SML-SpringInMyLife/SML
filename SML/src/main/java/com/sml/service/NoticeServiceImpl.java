package com.sml.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.controller.MemberController;
import com.sml.mapper.NoticeMapper;
import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.LikeVO;
import com.sml.model.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
 
	   private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	NoticeMapper noticeMapper;

	@Override // 게시글 등록
	public void noticeRegister(NoticeVO notice) throws Exception {

		noticeMapper.noticeRegister(notice);

		// 이미지가 없는 경우, 메서드 실행 종료
		if (notice.getImageList() == null || notice.getImageList().size() <= 0) {
			return;
		}

		// 이미지 등록, 람다식 활용한 for문
		notice.getImageList().forEach(attach -> {

			attach.setNoticeCode(notice.getNoticeCode());
			noticeMapper.imageEnroll(attach);
		});
	}

	@Override // 게시글 조회
	public List<NoticeVO> noticeGetList(Criteria cri) throws Exception {

		return noticeMapper.noticeGetList(cri);
	}

	@Override // 게시글 상세조회
	public NoticeVO noticeGetDetail(int noticeCode) throws Exception {

		return noticeMapper.noticeGetDetail(noticeCode);
	}

	@Transactional
	@Override  //게시글 수정
	public int noticeModify(NoticeVO noticevo) throws Exception {
		int result = noticeMapper.noticeModify(noticevo);

		// 기존 이미지 삭제
		noticeMapper.deleteImageAll(noticevo.getNoticeCode());

		// 새 이미지가 있으면 추가
		if (noticevo.getImageList() != null && !noticevo.getImageList().isEmpty()) {
			noticevo.getImageList().forEach(attach -> {
				attach.setNoticeCode(noticevo.getNoticeCode());
				noticeMapper.imageEnroll(attach);
			});
		}

		return result;
	}

	@Override // 게시글 삭제
	public int noticeDelete(int noticeCode) {

		return noticeMapper.noticeDelete(noticeCode);
	}

	@Override // 페이징 처리
	public int noticeGetTotal(Criteria cri) throws Exception {

		return noticeMapper.noticeGetTotal(cri);
	}

	@Override // 게시글 조회수 증가
	@Transactional
	public int noticeCount(int noticeCode) throws Exception {

		return noticeMapper.noticeCount(noticeCode);
	}
	
	@Override //좋아요 기능 
	public boolean toggleLike(int noticeCode, int memCode) {
	    List<LikeVO> existingLikes = noticeMapper.getLike(noticeCode, memCode);
	    boolean isLiked;
	    if (existingLikes == null || existingLikes.isEmpty()) {
	        LikeVO newLike = new LikeVO();
	        newLike.setMemCode(memCode);
	        newLike.setNoticeCode(noticeCode);
	        newLike.setLikeType("NOTICE");
	        noticeMapper.insertLike(newLike);
	        noticeMapper.updateNoticeLikeCount(noticeCode, 1);  // 직접 업데이트
	        isLiked = true;
	    } else {
	        LikeVO like = existingLikes.get(0);
	        noticeMapper.deleteLike(like.getLikeCode());
	        noticeMapper.updateNoticeLikeCount(noticeCode, -1);  // 직접 업데이트
	        isLiked = false;
	    }
	    return isLiked;
	}

	    @Override
	    public int getNoticeLikeCount(int noticeCode) {
	        return noticeMapper.getNoticeLikeCount(noticeCode);
	    }
	    
	}
	


