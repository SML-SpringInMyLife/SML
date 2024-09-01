package com.sml.model;

import lombok.Data;

@Data
public class PageDTO {

    // 시작/끝 페이지, 이전/다음 페이지 존재 유무, 전체 게시물 수
	private int pageStart;
    private int pageEnd;
    private boolean next, prev;
    private int total;
    
    /* 현재 페이지, 페이지당 게시물 표시 수 정보 */
    private Criteria cri;
    
    // constructor
    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;
        
        // 마지막 페이지
        this.pageEnd = (int)(Math.ceil(cri.getPageNum()/5.0))*5;
        
        // 시작 페이지
        this.pageStart = this.pageEnd - 4;
        
        // 전체 마지막 페이지
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
        
        // realEnd가 화면에 보이는 마지막 페이지보다 작은 경우 endPage 값 조정
        if(realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }
        
        // 시작 페이지 값이 1보다 큰 경우, 마지막 페이지 값이 1보다 큰 경우
        this.prev = this.pageStart > 1;
        this.next = this.pageEnd < realEnd;
        
    }

}