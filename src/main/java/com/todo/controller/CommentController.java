package com.todo.controller;

import com.todo.dto.CommentResponseDTO;
import com.todo.dto.UserResponseDTO;
import com.todo.entity.Comment;
import com.todo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CommentResponseDTO>> findAll() {
        List<CommentResponseDTO> users = commentService.findAll();
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long commentId) {
        CommentResponseDTO commentResponseDTO = commentService.findById(commentId);
        return ResponseEntity.ok(commentResponseDTO);
    }
}
