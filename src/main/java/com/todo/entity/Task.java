package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="task")
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String priority;
    private String title;
    private String description;
    private String status;
    private Long UserID;
    private Long CategoryID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

//    public Task(){
//
//    }
}
