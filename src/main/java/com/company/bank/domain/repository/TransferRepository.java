package com.company.bank.domain.repository;

import com.company.bank.domain.entity.Transfer;
import java.util.List;

/**
 * Puerto de salida para el manejo de persistencia de Transferencias.
 */
public interface TransferRepository {
    Transfer save(Transfer transfer);
    List<Transfer> findByAccountId(Long accountId);
}
