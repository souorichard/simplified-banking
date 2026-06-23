package com.simplifiedBanking.services;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simplifiedBanking.domain.user.User;
import com.simplifiedBanking.domain.user.UserType;
import com.simplifiedBanking.dtos.UserRequest;
import com.simplifiedBanking.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository repository;

  public User findUserById(UUID id) throws Exception {
    return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
  }

  public void saveUser(User user) {
    this.repository.save(user);
  }

  public void validateTransaction(User sender, BigInteger value) throws Exception {
    if (sender.getType() == UserType.MERCHANT) {
      throw new Exception("Users of the merchant type are not authorized to perform transactions");
    }

    if (sender.getBalance().compareTo(value) < 0) {
      throw new Exception("Insufficient balace");
    }
  }

  public User createUser(UserRequest data) {
    User newUser = new User(data);

    this.saveUser(newUser);

    return newUser;
  }

  public List<User> getAllUsers() {
    return this.repository.findAll();
  }
}
