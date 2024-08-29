package com.todo.controller;

import com.todo.dto.TaskResponseDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {

        return taskService.createTask(task);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        List<TaskResponseDTO> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    public String getTaskController() {
        return "TaskController endpointi.";
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable int taskId) {
        TaskResponseDTO taskResponseDTO = taskService.findById(taskId);
        return ResponseEntity.ok(taskResponseDTO);
    }
}

