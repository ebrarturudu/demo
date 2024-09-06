package com.todo.service;

import com.todo.dto.CommentRequestDTO;
import com.todo.dto.CommentResponseDTO;
import com.todo.entity.Comment;
import com.todo.entity.Task;
import com.todo.entity.User;
import com.todo.exception.CategoryNotFoundException;
import com.todo.exception.CommentNotFoundException;
import com.todo.exception.TaskNotFoundException;
import com.todo.repository.CommentRepository;
import com.todo.repository.TaskRepository;
import com.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {

       Comment comment = new Comment();

        comment.setText(commentRequestDTO.getText());

        Task task = taskRepository.findById(commentRequestDTO.getTaskId()).orElseThrow(() -> new TaskNotFoundException("task bulunamadı"));
        User user= userRepository.findById(commentRequestDTO.getUserId()).orElseThrow(()->new CategoryNotFoundException("category bulunamadı."));

        comment.setTask(task);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

        commentResponseDTO.setId(savedComment.getId());
        commentResponseDTO.setText(savedComment.getText());
        commentResponseDTO.setTaskId(savedComment.getTask().getId());
        commentResponseDTO.setUserId(savedComment.getUser().getId());

        return commentResponseDTO;
    }

    public List<CommentResponseDTO> findAll() {

        return commentRepository.findAll().stream().map(comment -> {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setText(comment.getText());
            commentResponseDTO.setUserId(comment.getId());
            return commentResponseDTO;
        }).collect(Collectors.toList());
    }

    public CommentResponseDTO findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id" + id + "not found"));

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setText(comment.getText());
        commentResponseDTO.setUserId(comment.getId());

        return commentResponseDTO;
    }

    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id" + id + "not found"));
        comment.setText(commentRequestDTO.getText());
        comment.setId(commentRequestDTO.getUserId());

        commentRepository.save(comment);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setText(commentRequestDTO.getText());
        commentResponseDTO.setUserId(comment.getId());

        return commentResponseDTO;
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment with id" + id + "not found");
        }
        commentRepository.deleteById(id);
        System.out.println("Deleted comment with id " + id);
    }

    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);}

    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);}
}
