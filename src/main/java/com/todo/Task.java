package com.todo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Task extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int priority;
    private String title;
    private String description;
    private String status;

    //TODO: Sınıflarda aynı olan parametreleri object orianted'ın yeteneklerini kullanarak uygun hale getir.

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comment;

}
