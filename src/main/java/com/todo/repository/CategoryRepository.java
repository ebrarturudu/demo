package com.todo.repository;

import com.todo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends  JpaRepository<Category, Integer>{
}
