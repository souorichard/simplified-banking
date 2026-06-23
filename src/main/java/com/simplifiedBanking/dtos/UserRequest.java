package com.simplifiedBanking.dtos;

import java.math.BigInteger;

import com.simplifiedBanking.domain.user.UserType;


public record UserRequest(
  String firstName,
  String lastName,
  String document,
  String email,
  String password,
  BigInteger balance,
  UserType type
) {}
