package com.simplifiedBanking.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplifiedBanking.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findUserById(UUID id);
  Optional<User> findUserByDocument(String document);
}
