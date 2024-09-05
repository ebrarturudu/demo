package com.todo.controller;

import com.todo.dto.CategoryRequestDTO;
import com.todo.dto.CategoryResponseDTO;
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
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.createCategory(categoryRequestDTO);
    }

    @GetMapping("/findall")
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable int categoryId) {
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(categoryId);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable int categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(categoryId, categoryRequestDTO);
        return ResponseEntity.ok(updatedCategory);

    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId){

        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
