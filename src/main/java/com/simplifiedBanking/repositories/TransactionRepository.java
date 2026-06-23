package com.simplifiedBanking.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplifiedBanking.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {}
