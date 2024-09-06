package com.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {

    private Long id;
    private Long userId;
    private String text;
    private Long taskId;
}
