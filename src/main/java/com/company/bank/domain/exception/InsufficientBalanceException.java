package com.company.bank.domain.exception;

/**
 * Excepción lanzada cuando no hay saldo suficiente para realizar una transferencia.
 */
public class InsufficientBalanceException extends BusinessRuleException {
    public InsufficientBalanceException(Long accountId) {
        super("Insufficient balance for account ID: " + accountId);
    }
}
