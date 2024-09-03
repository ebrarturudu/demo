package com.todo.dto;

import com.todo.entity.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO extends Task {
    private Long UserId;
    private int priority;
    private String title;
    private String description;
    private String status;
    private int id;

}
