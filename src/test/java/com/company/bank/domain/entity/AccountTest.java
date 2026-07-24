package com.company.bank.domain.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldWithdrawSuccessfully() {
        Account account = new Account(1L, "123", "Alice", new BigDecimal("1000.00"), "USD", LocalDateTime.now(), LocalDateTime.now());
        account.withdraw(new BigDecimal("200.00"));
        
        assertEquals(new BigDecimal("800.00"), account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenInsufficientBalance() {
        Account account = new Account(1L, "123", "Alice", new BigDecimal("100.00"), "USD", LocalDateTime.now(), LocalDateTime.now());
        
        Exception exception = assertThrows(com.company.bank.domain.exception.InsufficientBalanceException.class, () -> {
            account.withdraw(new BigDecimal("200.00"));
        });
        
        assertEquals("Insufficient balance for account ID: 1", exception.getMessage());
    }

    @Test
    void shouldDepositSuccessfully() {
        Account account = new Account(1L, "123", "Alice", new BigDecimal("1000.00"), "USD", LocalDateTime.now(), LocalDateTime.now());
        account.deposit(new BigDecimal("300.00"));
        
        assertEquals(new BigDecimal("1300.00"), account.getBalance());
    }
}
