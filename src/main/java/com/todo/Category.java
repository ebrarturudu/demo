package com.todo;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "task_id")
//    Task task;


    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> task;

}
