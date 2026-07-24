package com.company.bank.presentation.controller;

import com.company.bank.application.dto.AccountDto;
import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.usecase.GetAccountUseCase;
import com.company.bank.application.usecase.GetTransferHistoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Accounts", description = "Endpoints for managing bank accounts")
public class AccountController {

    private final GetAccountUseCase getAccountUseCase;
    private final GetTransferHistoryUseCase getTransferHistoryUseCase;

    public AccountController(GetAccountUseCase getAccountUseCase, GetTransferHistoryUseCase getTransferHistoryUseCase) {
        this.getAccountUseCase = getAccountUseCase;
        this.getTransferHistoryUseCase = getTransferHistoryUseCase;
    }

    /**
     * Obtiene la información de una cuenta.
     *
     * @param id identificador de la cuenta.
     * @return 200 OK con la información de la cuenta.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Retrieves account details including current balance.")
    public ResponseEntity<AccountDto> getAccount(
            @Parameter(description = "Account ID") @PathVariable Long id) {
        return ResponseEntity.ok(getAccountUseCase.getAccount(id));
    }

    /**
     * Obtiene el historial de transferencias de una cuenta.
     *
     * @param id identificador de la cuenta.
     * @return 200 OK con la lista de transferencias asociadas.
     */
    @GetMapping("/{id}/transfers")
    @Operation(summary = "Get account transfer history", description = "Retrieves all transfers made or received by the account.")
    public ResponseEntity<List<TransferDto>> getTransferHistory(
            @Parameter(description = "Account ID") @PathVariable Long id) {
        return ResponseEntity.ok(getTransferHistoryUseCase.getTransferHistory(id));
    }
}
