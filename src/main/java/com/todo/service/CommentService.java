package com.todo.service;

import com.todo.dto.CommentRequestDTO;
import com.todo.dto.CommentResponseDTO;
import com.todo.dto.TaskRequestDTO;
import com.todo.dto.TaskResponseDTO;
import com.todo.entity.BaseEntity;
import com.todo.entity.Comment;
import com.todo.entity.Task;
import com.todo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

//    public Comment createComment(Comment comment) {
//        return CommentRepository.save(comment);
//    }

    public Comment createComment(Comment commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getText());

        Comment save=commentRepository.save(comment);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();

        commentResponseDTO.setId(save.getId());
        commentResponseDTO.setText(comment.getText());

        return commentDTO;
    }

    public List<CommentResponseDTO> findAll() {

        return commentRepository.findAll().stream().map(comment -> {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
            commentResponseDTO.setText(comment.getText());
            return commentResponseDTO;
        }).collect(Collectors.toList());
    }

    public CommentResponseDTO findById(Long id) {
        Comment comment = commentRepository.findById(id).get();

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setText(comment.getText());

        return commentResponseDTO;
    }

    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO) {
        Comment comment= commentRepository.findById(id).get();
        comment.setText(commentRequestDTO.getText());

        commentRepository.save(comment);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setText(commentRequestDTO.getText());

        return commentResponseDTO;
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
