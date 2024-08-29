package com.todo.interfaces;

import com.todo.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAll();

    UserResponseDTO findById(int id);

    UserResponseDTO update(UserResponseDTO user);

    void deleteById(int id);

    //  UserResponseDTO add(UserResponseDTO user);
}

