package com.prompthub.category.model.dao;

import com.prompthub.category.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> findAll();
}
