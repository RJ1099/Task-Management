package com.codewithrj.taskproject.service;

import com.codewithrj.taskproject.dto.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto createUser(UsersDto usersDto);
    UsersDto getUserById(Long userid);
    List<UsersDto> getAllUsers();
    UsersDto updatedUser(Long userid, UsersDto usersDto);
    void deletedUser(Long userid);
}
