package com.todo.service;

import com.todo.dto.UserRequestDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private int id = 0;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        //user.setId(++id);
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());

        User save = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(save.getId());//kullanıcı id sini otomatik alır
        userResponseDTO.setName(save.getName());
        userResponseDTO.setEmail(save.getEmail());

        return userResponseDTO;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(user -> { //tüm kullanıcıları veritabanından alır
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setId(user.getId());
            return userResponseDTO;
        }).collect(Collectors.toList());  //streami listeye döndürür.
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).get();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setId(user.getId());

//        userResponseDTO.setTasks(user.getTasks().stream().map(task -> {
//            TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
//            taskResponseDTO.setId(task.getId());
//            taskResponseDTO.setTitle(task.getTitle());
//            taskResponseDTO.setDescription(task.getDescription());
//            taskResponseDTO.setStatus(task.getStatus());
//            taskResponseDTO.setPriority(task.getPriority());
//            return taskResponseDTO;
//
//        }).collect(Collectors.toList()));

        return userResponseDTO;
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

       userRepository.save(user);  //Database'e kaydeder

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        //user.setPassword(userRequestDTO.getPassword());

        return userResponseDTO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        // userResponseDTO.setMesaj("başarılı silme işlemi");
    }
}

