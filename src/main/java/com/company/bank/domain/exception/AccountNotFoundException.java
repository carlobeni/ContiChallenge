package com.company.bank.domain.exception;

/**
 * Excepción lanzada cuando una cuenta no existe.
 */
public class AccountNotFoundException extends BusinessRuleException {
    public AccountNotFoundException(Long accountId) {
        super("Account not found with ID: " + accountId);
    }
}
