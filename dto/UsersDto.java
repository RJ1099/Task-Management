package com.codewithrj.taskproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long id;
    private String name;
    private String email;
    private String password;
}
