package com.todo.service;

import com.todo.dto.TaskResponseDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.Task;
import com.todo.entity.User;
import com.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task taskDTO) {
        Task task = new Task();

        task.setId(taskDTO.getId());
        task.setPriority(taskDTO.getPriority());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        taskRepository.save(task);
        return taskDTO;
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll().stream().map(task -> {//tüm kullanıcıları veritabanından alır
            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
            taskResponseDTO.setTitle(task.getTitle());
            return taskResponseDTO;
        }).collect(Collectors.toList());//streami listeye döndürür.
    }

    public TaskResponseDTO findById(int id) {
        Task task = taskRepository.findById(id).get();

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTitle(task.getTitle());
        return taskResponseDTO;
    }
}

