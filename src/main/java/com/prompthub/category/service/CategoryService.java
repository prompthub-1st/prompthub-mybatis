package com.prompthub.category.service;

import com.prompthub.category.model.dao.CategoryMapper;
import com.prompthub.category.model.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> findAll() {
        return categoryMapper.findAll();
    }
}
