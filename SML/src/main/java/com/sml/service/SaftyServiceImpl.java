package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.mapper.SaftyMapper;
import com.sml.model.Criteria;
import com.sml.model.LikeVO;
import com.sml.model.NoticeVO;
import com.sml.model.SaftyVO;

@Service
public class SaftyServiceImpl implements SaftyService {

	@Autowired
	SaftyMapper saftyMapper;

	@Override // 게시글 등록
	public void saftyRegister(SaftyVO safty) throws Exception {

		saftyMapper.saftyRegister(safty);

		// 이미지가 없는 경우, 메서드 실행 종료
		if (safty.getImageList() == null || safty.getImageList().size() <= 0) {
			return;
		}

		// 이미지 등록, 람다식 활용한 for문
		safty.getImageList().forEach(attach -> {

			attach.setSaftyCode(safty.getSaftyCode());
			saftyMapper.imageEnroll(attach);
		});
	}

	@Override // 게시글 목록
	public List<SaftyVO> saftyGetList(Criteria cri) throws Exception {

		return saftyMapper.saftyGetList(cri);
	}

	@Override // 페이징 처리
	public int saftyGetTotal(Criteria cri) throws Exception {

		return saftyMapper.saftyGetTotal(cri);
	}

	@Override // 게시글 상세조회
	public SaftyVO saftyGetDetail(int saftyCode) throws Exception {

		return saftyMapper.saftyGetDetail(saftyCode);
	}

	@Override // 게시글 조회수 증가
	@Transactional
	public int saftyCount(int saftyCode) throws Exception {

		return saftyMapper.saftyCount(saftyCode);
	}

	@Override // 좋아요 기능
	public boolean toggleLike(int saftyCode, int memCode) {
		List<LikeVO> existingLikes = saftyMapper.getLike(saftyCode, memCode);
		boolean isLiked;
		if (existingLikes == null || existingLikes.isEmpty()) {
			LikeVO newLike = new LikeVO();
			newLike.setMemCode(memCode);
			newLike.setSaftyCode(saftyCode);
			newLike.setLikeType("SAFTY");
			saftyMapper.insertLike(newLike);
			saftyMapper.updateSaftyLikeCount(saftyCode, 1); // 직접 업데이트
			isLiked = true;
		} else {
			LikeVO like = existingLikes.get(0);
			saftyMapper.deleteLike(like.getLikeCode());
			saftyMapper.updateSaftyLikeCount(saftyCode, -1); // 직접 업데이트
			isLiked = false;
		}
		return isLiked;
	}

	@Override // 좋아요 기능
	public int getSaftyLikeCount(int saftyCode) {
		return saftyMapper.getSaftyLikeCount(saftyCode);
	}
	
	@Transactional
	@Override  //게시글 수정
	public int saftyModify(SaftyVO saftyvo) throws Exception {
		int result = saftyMapper.saftyModify(saftyvo);

		// 기존 이미지 삭제
		saftyMapper.deleteImageAll(saftyvo.getSaftyCode());

		// 새 이미지가 있으면 추가
		if (saftyvo.getImageList() != null && !saftyvo.getImageList().isEmpty()) {
			saftyvo.getImageList().forEach(attach -> {
				attach.setSaftyCode(saftyvo.getSaftyCode());
				saftyMapper.imageEnroll(attach);
			});
		}

		return result;
	}

	@Override // 게시글 삭제
	public int saftyDelete(int saftyCode) {

		return saftyMapper.saftyDelete(saftyCode);
	}
	
}
