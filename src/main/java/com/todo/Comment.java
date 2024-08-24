package com.todo;
import jakarta.persistence.*;

@Entity
public class Comment extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
//    private LocalDate dueDate;
//    private String user;
//    private String task;


    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    Task task;

}
