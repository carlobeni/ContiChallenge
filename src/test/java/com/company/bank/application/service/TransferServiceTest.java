package com.company.bank.application.service;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.dto.TransferRequestDto;
import com.company.bank.application.mapper.TransferMapper;
import com.company.bank.domain.entity.Account;
import com.company.bank.domain.entity.Transfer;
import com.company.bank.domain.exception.AccountNotFoundException;
import com.company.bank.domain.exception.InvalidTransferException;
import com.company.bank.domain.repository.AccountRepository;
import com.company.bank.domain.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransferRepository transferRepository;
    @Mock
    private TransferMapper transferMapper;

    @InjectMocks
    private TransferService transferService;

    private Account source;
    private Account destination;

    @BeforeEach
    void setUp() {
        source = new Account(1L, "123", "Alice", new BigDecimal("1000"), "USD", LocalDateTime.now(), LocalDateTime.now());
        destination = new Account(2L, "456", "Bob", new BigDecimal("500"), "USD", LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void performTransfer_Successful() {
        TransferRequestDto request = new TransferRequestDto(1L, 2L, new BigDecimal("200"));
        when(accountRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(source));
        when(accountRepository.findByIdForUpdate(2L)).thenReturn(Optional.of(destination));
        when(transferRepository.save(any(Transfer.class))).thenAnswer(i -> i.getArgument(0));
        when(transferMapper.toDto(any(Transfer.class))).thenReturn(new TransferDto(1L, 1L, 2L, new BigDecimal("200"), LocalDateTime.now()));

        TransferDto result = transferService.performTransfer(request);

        assertNotNull(result);
        assertEquals(new BigDecimal("800"), source.getBalance());
        assertEquals(new BigDecimal("700"), destination.getBalance());
        verify(accountRepository, times(2)).save(any(Account.class));
        verify(transferRepository, times(1)).save(any(Transfer.class));
    }

    @Test
    void performTransfer_SameAccount_ThrowsException() {
        TransferRequestDto request = new TransferRequestDto(1L, 1L, new BigDecimal("200"));

        assertThrows(InvalidTransferException.class, () -> transferService.performTransfer(request));
        verify(accountRepository, never()).save(any());
    }

    @Test
    void performTransfer_AccountNotFound_ThrowsException() {
        TransferRequestDto request = new TransferRequestDto(1L, 2L, new BigDecimal("200"));
        when(accountRepository.findByIdForUpdate(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> transferService.performTransfer(request));
    }
}
