package com.company.bank.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDto(
    Long id,
    String accountNumber,
    String ownerName,
    BigDecimal balance,
    String currency,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
