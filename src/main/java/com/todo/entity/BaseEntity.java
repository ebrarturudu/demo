package com.todo.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseEntity
{

    @Temporal(TemporalType.TIMESTAMP)
    protected  Date deletedDate;
    @CreatedDate
    protected LocalDateTime createDate;
    @LastModifiedDate
    protected  LocalDateTime updateDate;

    protected  boolean IsDeleted;

    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updateDate= LocalDateTime.now();
    }
}
