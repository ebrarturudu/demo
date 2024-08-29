package com.todo.service;

import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.Task;
import com.todo.entity.User;
import com.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

//    public TaskResponseDTO findByTitle(String title) {
//
//    }

    public TaskResponseDTO updateTask(int id, TaskRequestDTO taskRequestDTO) {

        Task task = taskRepository.findById(id).get();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(taskRequestDTO.getDescription());

        return taskResponseDTO;
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

//    public void deleteTaskByName(String taskName) {
//        Task task = taskRepository.findByName(taskName)
//                .orElseThrow(() -> new NoSuchElementException("Task not found with name: " + taskName));
//
//        taskRepository.delete(task);
//    }
}

