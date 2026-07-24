package com.company.bank.application.usecase;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.dto.TransferRequestDto;

public interface PerformTransferUseCase {
    /**
     * Realiza una transferencia bancaria entre dos cuentas.
     *
     * Esta operación verifica que ambas cuentas existan,
     * que el monto sea válido, que no sean la misma cuenta y
     * que la cuenta de origen tenga saldo suficiente.
     *
     * @param requestDto información de la transferencia a realizar.
     * @return TransferDto con el resultado de la transferencia guardada.
     * @throws com.company.bank.domain.exception.AccountNotFoundException si alguna de las cuentas no existe.
     * @throws com.company.bank.domain.exception.InsufficientBalanceException cuando no existe saldo suficiente.
     * @throws com.company.bank.domain.exception.InvalidTransferException cuando hay un error en los parámetros.
     */
    TransferDto performTransfer(TransferRequestDto requestDto);
}
