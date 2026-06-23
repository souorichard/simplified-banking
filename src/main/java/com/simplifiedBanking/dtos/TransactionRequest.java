package com.simplifiedBanking.dtos;

import java.math.BigInteger;
import java.util.UUID;

public record TransactionRequest(BigInteger value, UUID senderId, UUID receiverId) {}
