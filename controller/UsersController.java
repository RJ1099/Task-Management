package com.codewithrj.taskproject.controller;

import com.codewithrj.taskproject.dto.UsersDto;
import com.codewithrj.taskproject.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto){
        UsersDto user = usersService.createUser(usersDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable("userid") Long userid){
        UsersDto user = usersService.getUserById(userid);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersDto>> getAllUsers(){
        List<UsersDto> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/user/{userid}")
    public ResponseEntity<UsersDto> updatedUser(@PathVariable(name = "userid") Long userid,
                                                @RequestBody UsersDto usersDto){
        UsersDto user = usersService.updatedUser(userid,usersDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{userid}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "userid") Long userid){
        usersService.deletedUser(userid);
        return ResponseEntity.ok("User deleted successfully");
    }
}
