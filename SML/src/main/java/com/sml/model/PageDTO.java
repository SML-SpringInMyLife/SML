package com.sml.model;

import lombok.Data;

@Data
public class PageDTO {

    // 시작/끝 페이지, 이전/다음 페이지 존재 유무, 전체 게시물 수
    private int pageStart;
    private int pageEnd;
    private boolean next, prev;
    private int total;

    // 현재 페이지, 페이지당 게시물 표시 수 정보
    private Criteria cri;

    // constructor
    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // 페이지당 표시 수
        int perPageNum = 5;

        // 마지막 페이지
        int realEnd = (int) Math.ceil((double) total / cri.getAmount());

        // 현재 페이지의 끝 페이지 계산
        this.pageEnd = (int) (Math.ceil(cri.getPageNum() / (double) perPageNum)) * perPageNum;

        // 페이지 시작 페이지
        this.pageStart = this.pageEnd - (perPageNum - 1);

        // 마지막 페이지가 실제 페이지 수보다 클 경우 조정
        if (realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }

        // 이전 및 다음 페이지 존재 유무 설정
        this.prev = this.pageStart > 1;
        this.next = this.pageEnd < realEnd;
    }
}
