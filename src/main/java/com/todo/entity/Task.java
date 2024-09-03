package com.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="task")
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int priority;
    private String title;
    private String description;
    private String status;
    private Long UserID;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="users")
//    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    public Task(){

    }
}
