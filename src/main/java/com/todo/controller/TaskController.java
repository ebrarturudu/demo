package com.todo.controller;

import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
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

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable int taskId, @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO updateTask = taskService.updateTask(taskId, taskRequestDTO);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/delete/task")
//    public ResponseEntity<Void> deleteTask(@RequestParam String name) {
//        taskService.deleteTaskByName(name);
//        return ResponseEntity.noContent().build();
//    }
}
