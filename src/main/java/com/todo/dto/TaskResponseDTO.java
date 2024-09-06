package com.todo.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO {

    private String title;
    private String description;
    private String status;
    private String priority;
    private Long id;
    private Long userId;
    private Long categoryId;


}
