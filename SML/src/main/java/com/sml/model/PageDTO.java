package com.sml.model;

import lombok.Data;

@Data
public class PageDTO {
	
	private int pageStart;
	private int pageEnd;
	private boolean next, prev;
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		
		/* cri, total 초기화 */
        this.cri = cri;
        this.total = total;
        
        /* 페이지 끝 번호 */
        this.pageEnd = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
        
        /* 페이지 시작 번호 */
        this.pageStart = this.pageEnd - 9;
        
        /* 전체 마지막 페이지 번호 */
        int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
        
        /* 페이지 끝 번호 유효성 체크 */
        if(realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }
        
        /* 이전 버튼 값 초기화 */
        this.prev = this.pageStart > 1;
        
        /* 다음 버튼 값 초기화 */
        this.next = this.pageEnd < realEnd;
        
		
	}

}
