package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.mapper.NoticeMapper;
import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.NoticeLikeVO;
import com.sml.model.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {

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
	@Override
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
	public int noticeCount(int noticeCode) throws Exception {

		return noticeMapper.noticeCount(noticeCode);

	}

	@Override //좋아요 
	public boolean toggleMemberNoticeLike(int noticeCode, int memCode) throws Exception {
		NoticeLikeVO like = noticeMapper.getMemberNoticeLike(noticeCode, memCode);
		if (like == null) {
			// 좋아요가 없으면 추가
			noticeMapper.insertMemberNoticeLike(noticeCode, memCode);
			return true;
		} else {
			// 좋아요가 있으면 삭제
			noticeMapper.deleteMemberNoticeLike(noticeCode, memCode);
			return false;
		}
	}

	@Override
	public boolean toggleGuestNoticeLike(int noticeCode, String guestIdentifier) throws Exception {
		NoticeLikeVO like = noticeMapper.getGuestNoticeLike(noticeCode, guestIdentifier);
		if (like == null) {
			// 좋아요가 없으면 추가
			noticeMapper.insertGuestNoticeLike(noticeCode, guestIdentifier);
			return true;
		} else {
			// 좋아요가 있으면 삭제
			noticeMapper.deleteGuestNoticeLike(noticeCode, guestIdentifier);
			return false;
		}
	}

	@Override
	public int getTotalLikes(int noticeCode) throws Exception {
		return noticeMapper.getTotalLikes(noticeCode);
	}

	@Override
	public boolean isLikedByMember(int noticeCode, int memCode) throws Exception {
		return noticeMapper.getMemberNoticeLike(noticeCode, memCode) != null;
	}

	@Override
	public boolean isLikedByGuest(int noticeCode, String guestIdentifier) throws Exception {
		return noticeMapper.getGuestNoticeLike(noticeCode, guestIdentifier) != null;
	}
  
	@Override
	public boolean toggleNoticeLike(int noticeCode, String identifier) throws Exception {
	    // identifier가 숫자면 회원, 아니면 비회원으로 처리
	    if (identifier.matches("\\d+")) {
	        return toggleMemberNoticeLike(noticeCode, Integer.parseInt(identifier));
	    } else {
	        return toggleGuestNoticeLike(noticeCode, identifier);
	    }
	}
}
