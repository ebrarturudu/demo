package com.todo.controller;

import com.todo.dto.CategoryResponseDTO;
import com.todo.entity.Category;
import com.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable int categoryId) {
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(categoryId);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping
    public String getCategories() {
        return "Category endpointi.";
    }

}
