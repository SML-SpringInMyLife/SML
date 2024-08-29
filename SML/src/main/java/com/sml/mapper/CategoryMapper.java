package com.sml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sml.model.CategoryVO;

@Mapper
public interface CategoryMapper {

	List<CategoryVO> getCategoriesByRange(@Param("start") int start, @Param("end") int end);

	void insertCategory(CategoryVO category);

	void updateCategory(CategoryVO category);

	void deleteCategory(int categoryCode);

}
