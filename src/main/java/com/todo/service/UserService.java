package com.todo.service;

import com.todo.dto.UserRequestDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.todo.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private int id = 0;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setId(++id);
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());

        User save = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setName(save.getName());
        userResponseDTO.setEmail(save.getEmail());
        userResponseDTO.setMesaj("Başarılı kayıt işlemi");

        return userResponseDTO;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(user -> { //tüm kullanıcıları veritabanından alır
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            return userResponseDTO;
        }).collect(Collectors.toList());  //streami listeye döndürür.
    }

    public UserResponseDTO findById(int id) {
        User user = userRepository.findById(id).get();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }

    public UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO){
        User user = userRepository.findById(id).get();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());

        userRepository.save(user);  //Database'e kaydeder

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        userResponseDTO.setMesaj("Başarılı güncelleme işlemi");
        return userResponseDTO;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }



}

