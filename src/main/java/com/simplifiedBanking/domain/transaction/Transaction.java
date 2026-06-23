package com.simplifiedBanking.domain.transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

import com.simplifiedBanking.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "transactions")
@Entity(name = "transactions")
@EqualsAndHashCode(of = "id")
public class Transaction {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "amount")
  private BigInteger value;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User receiver;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
