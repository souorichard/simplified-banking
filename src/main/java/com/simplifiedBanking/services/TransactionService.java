package com.simplifiedBanking.services;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplifiedBanking.domain.transaction.Transaction;
import com.simplifiedBanking.domain.user.User;
import com.simplifiedBanking.dtos.TransactionRequest;
import com.simplifiedBanking.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
  
  private final RestTemplate restTemplate;
  private final UserService userService;
  private final NotificationService notificationService;

  private final TransactionRepository repository;

  @Value("${authorize.transaction.url}")
  private String authorizeTransactionUrl;

  public Transaction createTransaction(TransactionRequest data) throws Exception {
    User sender = this.userService.findUserById(data.senderId());
    User receiver = this.userService.findUserById(data.receiverId());

    userService.validateTransaction(sender, data.value());

    Boolean isAuthorized = this.authorizeTransaction(sender, data.value());

    if (!isAuthorized) {
      throw new Exception("Transaction not authorized");
    }

    Transaction newTransaction = new Transaction();
    newTransaction.setValue(data.value());
    newTransaction.setSender(sender);
    newTransaction.setReceiver(receiver);
    newTransaction.setCreatedAt(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(data.value()));
    receiver.setBalance(receiver.getBalance().add(data.value()));

    this.repository.save(newTransaction);
    this.userService.saveUser(sender);
    this.userService.saveUser(receiver);

    this.notificationService.sendNotification(sender, "Transaction completed successfully");
    this.notificationService.sendNotification(receiver, "Transaction completed successfully");

    return newTransaction;
  }

  public boolean authorizeTransaction(User sender, BigInteger value) {
    @SuppressWarnings("unchecked")
    ResponseEntity<Map<String, Object>> authorizationResponse =
      restTemplate.getForEntity(authorizeTransactionUrl, (Class<Map<String, Object>>) (Class<?>) Map.class);

    if (authorizationResponse.getStatusCode() != HttpStatus.OK) return false;

    @SuppressWarnings("unchecked")
    Map<String, Object> data = (Map<String, Object>) authorizationResponse.getBody().get("data");
    
    return Boolean.TRUE.equals(data.get("authorization"));
}
}
