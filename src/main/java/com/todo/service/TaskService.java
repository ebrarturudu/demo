package com.todo.service;

import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
import com.todo.entity.Task;
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


    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        task.setUser(userRepository.findById(taskRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        task.setPriority(taskRequestDTO.getPriority());
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        taskRepository.save(task);
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setStatus(task.getStatus());
        taskResponseDTO.setUserId(task.getUserID());
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
//public List<TaskResponseDTO> findAll() {
//    return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
//}

    public TaskResponseDTO findById(Long id) {
        Task task = taskRepository.findById(id).get();
                //.orElseThrow(() -> new RuntimeException("Task not found"));
        //
//            return convertToDTO(task);
//        }

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setStatus(task.getStatus());
        return taskResponseDTO;}

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());
        task.setPriority(taskRequestDTO.getPriority());

        taskRepository.save(task);
//        return convertToDTO(task);
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(taskRequestDTO.getDescription());
        taskResponseDTO.setPriority(task.getPriority());
        taskResponseDTO.setStatus(task.getStatus());
        return taskResponseDTO;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

//    private TaskResponseDTO convertToDTO(Task task) {
//        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
//        taskResponseDTO.setUserId(task.getId());
//        taskResponseDTO.setTitle(task.getTitle());
//        taskResponseDTO.setDescription(task.getDescription());
//        taskResponseDTO.setPriority(task.getPriority());
//        taskResponseDTO.setStatus(task.getStatus());
//        taskResponseDTO.setUserId(task.getUser().getId()); // userId alanını ayarlayın
//        return taskResponseDTO;
//    }
}

//        User user = userRepository.findById(taskRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
//
//        Task task = new Task();
//
//        task.setPriority(taskRequestDTO.getPriority());
//        task.setTitle(taskRequestDTO.getTitle());
//        task.setDescription(taskRequestDTO.getDescription());
//        task.setStatus(taskRequestDTO.getStatus());
//        task.setId(taskRequestDTO.getId());
//        task.setUser(user);
//
//        taskRepository.save(task);
//
//        return convertToDTO(task);


