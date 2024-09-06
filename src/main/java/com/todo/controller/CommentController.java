package com.todo.controller;

import com.todo.dto.CommentRequestDTO;
import com.todo.dto.CommentResponseDTO;
import com.todo.entity.Comment;
import com.todo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create/{userId}/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO) {

        return commentService.createComment(commentRequestDTO);
    }

    @GetMapping("/findall")
    public List<CommentResponseDTO> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long commentId) {
        CommentResponseDTO commentResponseDTO = commentService.findById(commentId);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentResponseDTO> update(@PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO updatedComment = commentService.updateComment(commentId, commentRequestDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find/{taskId}")
    public List<Comment> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.getCommentsByUserId(taskId);
    }
    @GetMapping("find/{userId}")
    public List<Comment> getCommentsByUserId(@PathVariable Long userId) {
        return commentService.getCommentsByTaskId(userId);
    }
}
