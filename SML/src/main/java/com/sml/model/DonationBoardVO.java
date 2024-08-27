package com.sml.model;

import java.util.Date;
import lombok.Data;

@Data
public class DonationBoardVO {
    private Long id;           // 게시글 ID
    private String title;      // 게시글 제목
    private String content;    // 게시글 내용
    private Date createdDate;  // 작성일
    private Date updatedDate;  // 수정일
}
