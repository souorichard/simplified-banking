package com.simplifiedBanking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplifiedBanking.domain.transaction.Transaction;
import com.simplifiedBanking.dtos.TransactionRequest;
import com.simplifiedBanking.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
  
  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest data) throws Exception {
    Transaction newTransaction = this.transactionService.createTransaction(data);

    return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
  }
}
