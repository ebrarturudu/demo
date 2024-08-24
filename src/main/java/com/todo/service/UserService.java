package com.todo.service;

import com.todo.BaseEntity;
import org.springframework.stereotype.Service;
import com.todo.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public BaseEntity.User createUser(BaseEntity.User user) {
        return userRepository.save(user);
    }
}
