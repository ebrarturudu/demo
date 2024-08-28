package com.todo.service;

import com.todo.dto.CategoryResponseDTO;
import com.todo.entity.Category;
import com.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    public Category createCategory(Category category) {
//
//        return categoryRepository.save(category);
//    }

    public Category createCategory(Category categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setId(categoryDTO.getId());
        categoryRepository.save(category);

        return categoryDTO;
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setName(category.getName());
            return categoryResponseDTO;
        }).collect(Collectors.toList());
    }

    public CategoryResponseDTO findById(int id) {
        Category category = categoryRepository.findById(id).get();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(category.getName());

        return categoryResponseDTO;
    }
}
