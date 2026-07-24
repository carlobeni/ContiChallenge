package com.company.bank.application.usecase;

import com.company.bank.application.dto.TransferDto;
import java.util.List;

public interface GetTransferHistoryUseCase {
    /**
     * Obtiene el historial de transferencias (realizadas y recibidas) de una cuenta.
     * 
     * @param accountId identificador único de la cuenta.
     * @return Lista de TransferDto con el historial de la cuenta.
     */
    List<TransferDto> getTransferHistory(Long accountId);
}
