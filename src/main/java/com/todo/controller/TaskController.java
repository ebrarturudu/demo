package com.todo.controller;

import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
import com.todo.entity.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create/{userId}/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(taskRequestDTO);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        List<TaskResponseDTO> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long taskId) {
        TaskResponseDTO taskResponseDTO = taskService.findById(taskId);
        return ResponseEntity.ok(taskResponseDTO);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO updateTask = taskService.updateTask(taskId, taskRequestDTO);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find/{userId}")
    public List<Task> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @GetMapping("find/{categoryId}")
    public List<Task> getTasksByCategoryId(@PathVariable Long categoryId) {
        return taskService.getTasksByCategoryId(categoryId);
    }

}
