package com.todo.service;

import com.todo.dto.CategoryRequestDTO;
import com.todo.dto.CategoryResponseDTO;
import com.todo.entity.BaseEntity;
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

    public Category createCategory(Category categoryDTO) {
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setId(categoryDTO.getId());

        Category save = categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setId(save.getId());
        categoryResponseDTO.setName(save.getName());
        categoryResponseDTO.setDescription(save.getDescription());

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

//    public CategoryResponseDTO updateCategory(Category categoryDTO) {
//        Category category = categoryRepository.findById(categoryDTO.getId()).get();
//        category.setName(categoryDTO.getName());
//        category.setDescription(categoryDTO.getDescription());
//        category.setId(categoryDTO.getId());
//
//        categoryRepository.save(category);
//
//        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
//        categoryResponseDTO.setName(categoryDTO.getName());
//        be.setMesaj("başarılı güncelleme");
//        return categoryResponseDTO;
//    }

    public CategoryResponseDTO updateCategory(int id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).get();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setId(category.getId());

        categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(categoryRequestDTO.getName());

        return categoryResponseDTO;
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }


}
