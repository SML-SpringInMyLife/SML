package com.sml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sml.mapper.CategoryMapper;
import com.sml.model.CategoryType;
import com.sml.model.CategoryVO;

@Service
public class CategoryServiceImpl implements CategoryService {
    
	 @Autowired
	    private CategoryMapper categoryMapper;

	    @Override
	    public List<CategoryVO> getCategoriesByRange(int start, int end) {
	        return categoryMapper.getCategoriesByRange(start, end);
	    }

	    @Override
	    public void addCategory(CategoryVO category) {
	        categoryMapper.insertCategory(category);
	    }

	    @Override
	    public void updateCategory(CategoryVO category) {
	        categoryMapper.updateCategory(category);
	    }

	    @Override
	    public void deleteCategory(int categoryCode) {
	        categoryMapper.deleteCategory(categoryCode);
	    }
	}
