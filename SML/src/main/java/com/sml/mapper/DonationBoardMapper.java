package com.sml.mapper;

import com.sml.model.DonationBoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonationBoardMapper {

    // 전체 후원 게시판 목록 가져오기
    List<DonationBoardVO> getAllBoards();

    // 특정 ID로 게시판 상세 정보 가져오기
    DonationBoardVO getBoardById(@Param("id") Long id);

    // 게시판 글 저장 (새로운 글 작성 또는 수정)
    void saveBoard(DonationBoardVO board);

    // 게시판 글 삭제
    void deleteBoard(@Param("id") Long id);
}
