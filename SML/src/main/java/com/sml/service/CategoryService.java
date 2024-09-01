package com.sml.service;

import java.util.List;

import com.sml.model.CategoryType;
import com.sml.model.CategoryVO;

public interface CategoryService {
	 
	List<CategoryVO> getCategoriesByRange(int start, int end);
    void addCategory(CategoryVO category);
    void updateCategory(CategoryVO category);
    void deleteCategory(int categoryCode);
	
}
