package com.company.bank.application.service;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.dto.TransferRequestDto;
import com.company.bank.application.mapper.TransferMapper;
import com.company.bank.application.usecase.PerformTransferUseCase;
import com.company.bank.domain.entity.Account;
import com.company.bank.domain.entity.Transfer;
import com.company.bank.domain.exception.AccountNotFoundException;
import com.company.bank.domain.exception.InvalidTransferException;
import com.company.bank.domain.repository.AccountRepository;
import com.company.bank.domain.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Servicio de Aplicación para gestionar operaciones transaccionales.
 * Implementa el caso de uso para realizar una transferencia aplicando el patrón de arquitectura limpia.
 */
@Service
public class TransferService implements PerformTransferUseCase {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    public TransferService(AccountRepository accountRepository, TransferRepository transferRepository, TransferMapper transferMapper) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.transferMapper = transferMapper;
    }

    @Override
    @Transactional
    public TransferDto performTransfer(TransferRequestDto requestDto) {
        if (requestDto.sourceAccountId().equals(requestDto.destinationAccountId())) {
            throw new InvalidTransferException("Source and destination accounts cannot be the same.");
        }

        Long sourceId = requestDto.sourceAccountId();
        Long destId = requestDto.destinationAccountId();
        Account sourceAccount, destinationAccount;

        // Evitar deadlocks adquiriendo los locks en orden de ID
        if (sourceId < destId) {
            sourceAccount = accountRepository.findByIdForUpdate(sourceId)
                    .orElseThrow(() -> new AccountNotFoundException(sourceId));
            destinationAccount = accountRepository.findByIdForUpdate(destId)
                    .orElseThrow(() -> new AccountNotFoundException(destId));
        } else {
            destinationAccount = accountRepository.findByIdForUpdate(destId)
                    .orElseThrow(() -> new AccountNotFoundException(destId));
            sourceAccount = accountRepository.findByIdForUpdate(sourceId)
                    .orElseThrow(() -> new AccountNotFoundException(sourceId));
        }

        // Aplicar la regla de negocio de retiro y depósito (dominio rico)
        sourceAccount.withdraw(requestDto.amount());
        destinationAccount.deposit(requestDto.amount());

        // Guardar las cuentas actualizadas
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        // Registrar la transferencia
        Transfer transfer = new Transfer(
                null,
                sourceAccount.getId(),
                destinationAccount.getId(),
                requestDto.amount(),
                LocalDateTime.now()
        );
        Transfer savedTransfer = transferRepository.save(transfer);

        return transferMapper.toDto(savedTransfer);
    }
}
