package com.prompthub.category.controller;

import com.prompthub.category.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Map<String, Object> getCategories() {

        return Map.of(
                "success", true,
                "data", categoryService.findAll(),
                "message", "OK"
        );
    }
}
