package com.todo.service;

import com.todo.Comment;
import com.todo.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository CommentRepository;

    public Comment createComment(Comment comment) {
        return CommentRepository.save(comment);

    }
}
