package com.todo.controller;

import com.todo.dto.UserRequestDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser).getBody();
        //return userService.createUser(userRequestDTO);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable int userId) {
        UserResponseDTO userResponseDTO = userService.findById(userId);
        return ResponseEntity.ok(userResponseDTO);
    }

//    @PutMapping("/users/{userId}")
//    public ResponseEntity<UserResponseDTO> update(@PathVariable int userId, @RequestBody UserRequestDTO userRequestDTO) {
//        UserResponseDTO updatedUser= userService.updateUser(userId, userRequestDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
}
//@DeleteMapping("/users/{userId}")
//    public void deleteById(@PathVariable int userId) {
//
//}
//@PostMapping("/users")
//    public UserResponseDTO add(@RequestBody UserRequestDTO userRequestDTO) {
//
//}

