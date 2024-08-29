package com.todo.entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
public class BaseEntity
{

    @Temporal(TemporalType.TIMESTAMP)
    protected  Date deletedDate;
    @CreatedDate
    protected  Date createDate;
    @LastModifiedDate
    protected  Date updateDate;
    
    protected  boolean isDeleted;

}
