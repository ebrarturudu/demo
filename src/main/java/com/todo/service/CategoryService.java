package com.todo.service;

import com.todo.dto.CategoryRequestDTO;
import com.todo.dto.CategoryResponseDTO;
import com.todo.entity.Category;
import com.todo.entity.User;
import com.todo.exception.CategoryNotFoundException;
import com.todo.exception.UserNotFoundException;
import com.todo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    //@Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();

        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());

        Category save = categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setId(save.getId());
        categoryResponseDTO.setName(save.getName());
        categoryResponseDTO.setDescription(save.getDescription());

        return categoryResponseDTO;
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setName(category.getName());
            categoryResponseDTO.setDescription(category.getDescription());
            return categoryResponseDTO;
        }).collect(Collectors.toList());
    }

    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("User with id " + id + " not found"));

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        categoryResponseDTO.setId(category.getId());

        return categoryResponseDTO;
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("User with id " + id + " not found"));
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setId(category.getId());

        categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(categoryRequestDTO.getName());
        categoryResponseDTO.setDescription(categoryRequestDTO.getDescription());
        categoryResponseDTO.setId(category.getId());

        return categoryResponseDTO;
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with id " + id + " not found");

        }
        categoryRepository.deleteById(id);
        System.out.println("Category deleted" + id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("User with id " + id + " not found"));
    }
}
