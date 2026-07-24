package com.company.bank.application.service;

import com.company.bank.application.dto.AccountDto;
import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.mapper.AccountMapper;
import com.company.bank.application.mapper.TransferMapper;
import com.company.bank.application.usecase.GetAccountUseCase;
import com.company.bank.application.usecase.GetTransferHistoryUseCase;
import com.company.bank.domain.entity.Account;
import com.company.bank.domain.exception.AccountNotFoundException;
import com.company.bank.domain.repository.AccountRepository;
import com.company.bank.domain.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de Aplicación para gestionar las operaciones de Cuentas.
 * Implementa los casos de uso para lectura de cuenta y su historial.
 */
@Service
public class AccountService implements GetAccountUseCase, GetTransferHistoryUseCase {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final AccountMapper accountMapper;
    private final TransferMapper transferMapper;

    public AccountService(AccountRepository accountRepository, TransferRepository transferRepository, AccountMapper accountMapper, TransferMapper transferMapper) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.accountMapper = accountMapper;
        this.transferMapper = transferMapper;
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return accountMapper.toDto(account);
    }

    @Override
    public List<TransferDto> getTransferHistory(Long accountId) {
        // Verificar que la cuenta existe primero
        accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
                
        return transferMapper.toDtoList(transferRepository.findByAccountId(accountId));
    }
}
