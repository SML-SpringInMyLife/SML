package com.sml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sml.model.Criteria;
import com.sml.model.FileupVO;
import com.sml.model.LikeVO;
import com.sml.model.NoticeVO;
import com.sml.model.SaftyVO;

public interface SaftyMapper {
	/* 공지사항 등록 */
	public int saftyRegister(SaftyVO safty);

	/* 이미지 등록 */
	public void imageEnroll(FileupVO vo);

	/* 지정 이미지 전체 삭제 */
	public void deleteImageAll(int saftyCode);
	
	/* 생활안전 조회(목록) */
	public List<SaftyVO> saftyGetList(Criteria cri);

	/* 생활안전 페이징 */
	public int saftyGetTotal(Criteria cri);
	
	/* 생활안전 상세조회 */
	public SaftyVO saftyGetDetail(int saftyCode);

	/* 생활안전 조회수 증가 기능 */
	public int saftyCount(int saftyCode);

	/* 생활안전 좋아요 */
	List<LikeVO> getLike(@Param("saftyCode") int saftyCode, @Param("memCode") int memCode);

	void insertLike(LikeVO like);

	void deleteLike(int likeCode);

	void updateSaftyLikeCount(@Param("saftyCode") int saftyCode, @Param("increment") int increment);

	int getSaftyLikeCount(int saftyCode);
	
	/* 생활안전 수정 */
	public int saftyModify(SaftyVO saftyvo);

	/* 생활안전 삭제 */
	public int saftyDelete(int saftyCode);

	/* 생활안전 카테고리 */
	List<SaftyVO> getSaftyList(@Param("cri") Criteria cri);

	int getSaftyTotal(@Param("cri") Criteria cri);

}
