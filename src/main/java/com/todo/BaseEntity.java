package com.todo;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MappedSuperclass
public class BaseEntity
{

    @CreatedDate
    protected  LocalDate date;
    @CreatedDate
    protected  Date dueDate;
    @CreatedDate
    protected  Date createDate;
    @CreatedDate
    protected  Date updateDate;

    protected  boolean isDeleted;

    @Entity
    @Table(name = "Users")
    public static class User extends BaseEntity
     {
        //Her nesne için belli başlı fieldları kesinlikle tutmalısın.
        //createdDate
        //updateDate
        //deletedDate
        //isDeleted

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String username;
        private String email;
        private String password;

        @OneToMany(mappedBy ="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Task> tasks = new ArrayList<>();

        @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Comment> comments = new ArrayList<>();

    }
}
