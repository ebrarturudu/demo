package com.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {
    private int priority;
    private String title;
    private String description;
    private String status;
}
