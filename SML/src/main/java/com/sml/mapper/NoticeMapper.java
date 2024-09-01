package com.sml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.LikeVO;
import com.sml.model.NoticeVO;

public interface NoticeMapper {
	/* 공지사항 등록 */
	public int noticeRegister(NoticeVO notice);

	/* 이미지 등록 */
	public void imageEnroll(FileupVO vo);

	/* 지정 이미지 전체 삭제 */
	public void deleteImageAll(int noticeCode);

	/* 공지사항 조회(목록) */
	public List<NoticeVO> noticeGetList(Criteria cri);

	/* 공지사항 페이징 */
	public int noticeGetTotal(Criteria cri);

	/* 공지사항 상세조회 */
	public NoticeVO noticeGetDetail(int noticeCode);

	/* 공지사항 조회수 증가 기능 */
	public int noticeCount(int noticeCode);

	/* 공지사항 좋아요 */
	List<LikeVO> getLike(@Param("noticeCode") int noticeCode, @Param("memCode") int memCode);

	void insertLike(LikeVO like);

	void deleteLike(int likeCode);

	void updateNoticeLikeCount(@Param("noticeCode") int noticeCode, @Param("increment") int increment);

	int getNoticeLikeCount(int noticeCode);

	/* 공지사항 수정 */
	public int noticeModify(NoticeVO noticevo);

	/* 공지사항 삭제 */
	public int noticeDelete(int noticeCode);

	/* 공지사항 카테고리 */
	List<NoticeVO> getNoticeList(@Param("cri") Criteria cri);

	int getNoticeTotal(@Param("cri") Criteria cri);

}
