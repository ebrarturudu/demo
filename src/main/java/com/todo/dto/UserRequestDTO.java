package com.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDTO {//kullanıcı oluşturma ve güncelleme işlemlerinde kullanacağımız veriler
    private String name;
    private String email;
    private String password;


}
