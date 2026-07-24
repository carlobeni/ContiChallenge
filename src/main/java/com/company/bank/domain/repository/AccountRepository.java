package com.company.bank.domain.repository;

import com.company.bank.domain.entity.Account;
import java.util.Optional;

/**
 * Puerto de salida para el manejo de persistencia de Cuentas.
 * Permite a la capa de aplicación interactuar con los datos sin
 * acoplarse a un framework de bases de datos.
 */
public interface AccountRepository {
    Optional<Account> findById(Long id);
    Optional<Account> findByIdForUpdate(Long id);
    Account save(Account account);
}
