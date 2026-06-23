package com.simplifiedBanking.domain.user;

import java.math.BigInteger;
import java.util.UUID;

import com.simplifiedBanking.dtos.UserRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "users")
@EqualsAndHashCode(of = "id")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String document;

  @Column(unique = true)
  private String email;

  private String password;
  private BigInteger balance;

  @Enumerated(EnumType.STRING)
  private UserType type;

  public User(UserRequest data) {
    this.firstName = data.firstName();
    this.lastName = data.lastName();
    this.document = data.document();
    this.email = data.email();
    this.password = data.password();
    this.balance = data.balance();
    this.type = data.type();
  }
}
