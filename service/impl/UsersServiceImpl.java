package com.codewithrj.taskproject.service.impl;

import com.codewithrj.taskproject.dto.UsersDto;
import com.codewithrj.taskproject.entity.Users;
import com.codewithrj.taskproject.exceptions.UserNotFound;
import com.codewithrj.taskproject.repository.UsersRepository;
import com.codewithrj.taskproject.service.UsersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        Users user = modelMapper.map(usersDto, Users.class);
        Users savedUser = usersRepository.save(user);
        return modelMapper.map(savedUser,UsersDto.class);
    }

    @Override
    public UsersDto getUserById(Long userid) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));
        return modelMapper.map(user,UsersDto.class);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user,UsersDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto updatedUser(Long userid, UsersDto usersDto) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()->new UserNotFound(String.format("User with id %d is not found", userid)));

        user.setName(usersDto.getName());
        user.setEmail(usersDto.getEmail());
        user.setPassword(usersDto.getPassword());
        usersRepository.save(user);
        return modelMapper.map(user,UsersDto.class);
    }

    @Override
    public void deletedUser(Long userid) {
        Users user = usersRepository.findById(userid)
                .orElseThrow(()-> new UserNotFound(String.format("User with id %d is not found",userid)));

        usersRepository.deleteById(userid);
    }
}
