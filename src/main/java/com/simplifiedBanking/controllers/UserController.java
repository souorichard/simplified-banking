package com.simplifiedBanking.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplifiedBanking.domain.user.User;
import com.simplifiedBanking.dtos.UserRequest;
import com.simplifiedBanking.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserRequest data) {
    User newUser = this.userService.createUser(data);

    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = this.userService.getAllUsers();

    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}
