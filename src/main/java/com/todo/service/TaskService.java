package com.todo.service;

import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
import com.todo.entity.Category;
import com.todo.entity.Task;
import com.todo.entity.User;
import com.todo.exception.TaskNotFoundException;
import com.todo.repository.CategoryRepository;
import com.todo.repository.TaskRepository;
import com.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {

        Task task = new Task();

        task.setPriority(taskRequestDTO.getPriority());
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        User user = userRepository.findById(taskRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Category category = categoryRepository.findById(taskRequestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        task.setUser(user);
        task.setCategory(category);

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(savedTask.getId());
        taskResponseDTO.setPriority(savedTask.getPriority());
        taskResponseDTO.setTitle(savedTask.getTitle());
        taskResponseDTO.setDescription(savedTask.getDescription());
        taskResponseDTO.setStatus(savedTask.getStatus());
        taskResponseDTO.setUserId(savedTask.getUser().getId());
        taskResponseDTO.setCategoryId(savedTask.getCategory().getId());

        return taskResponseDTO;
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll().stream().map(task -> {//tüm kullanıcıları veritabanından alır
            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
            taskResponseDTO.setTitle(task.getTitle());
            taskResponseDTO.setDescription(task.getDescription());
            taskResponseDTO.setPriority(task.getPriority());
            taskResponseDTO.setStatus(task.getStatus());
            return taskResponseDTO;
        }).collect(Collectors.toList());//streami listeye döndürür.
    }

    public TaskResponseDTO findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("task with id " + id + " not found"));
        ;

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setStatus(task.getStatus());

        return taskResponseDTO;
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + "not found"));
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setPriority(taskRequestDTO.getPriority());

        taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(taskRequestDTO.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setStatus(task.getStatus());
        return taskResponseDTO;
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
        System.out.println("Deleted task with id " + id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getTasksByCategoryId(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }
}

