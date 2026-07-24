package com.company.bank.domain.exception;

/**
 * Excepción lanzada cuando los parámetros de la transferencia son inválidos.
 */
public class InvalidTransferException extends BusinessRuleException {
    public InvalidTransferException(String message) {
        super(message);
    }
}
