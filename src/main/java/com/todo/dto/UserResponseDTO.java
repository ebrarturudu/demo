package com.todo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {//kullanıcıya sunacağımız veriler. Genellikle kullanıcı bilgilerini listeleme ve tek bir kullanıcıyı getirme işlemlerinde kullanılır.
    private int id;
    private String name;
    private String email;
}
