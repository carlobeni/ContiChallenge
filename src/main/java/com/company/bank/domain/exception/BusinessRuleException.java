package com.company.bank.domain.exception;

/**
 * Excepción base para reglas de negocio no cumplidas.
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
