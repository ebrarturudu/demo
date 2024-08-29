package com.todo.dataAccess;

import com.todo.dto.UserResponseDTO;

import java.util.List;

public interface IUserDAO {

    List<UserResponseDTO> findAll();
    UserResponseDTO findById(int id);
    UserResponseDTO update(UserResponseDTO user);
    void deleteById(int id);
  //  UserResponseDTO add(UserResponseDTO user);
}

