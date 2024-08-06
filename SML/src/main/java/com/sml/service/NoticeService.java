package com.sml.service;

import java.util.List;

import com.sml.model.FileupVO;
import com.sml.model.NoticeVO;

public interface NoticeService {

	/* 공지사항 등록 */                             //파일을 여러게 등록할 수 있으므로 list사용
	public void noticeRegisterfile(NoticeVO notice, List<FileupVO> fileup);
}
