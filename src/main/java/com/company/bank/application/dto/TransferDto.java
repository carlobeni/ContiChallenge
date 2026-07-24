package com.company.bank.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferDto(
    Long id,
    Long sourceAccountId,
    Long destinationAccountId,
    BigDecimal amount,
    LocalDateTime createdAt
) {}
