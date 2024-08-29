package com.sml.mapper;

import java.util.List;

import com.sml.model.FileupVO;

public interface AttachMapper {
	/* 이미지 데이터 반환 */
	public List<FileupVO> getAttachList(int noticeCode);

}
