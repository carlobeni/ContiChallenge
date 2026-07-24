package com.company.bank.presentation.controller;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.dto.TransferRequestDto;
import com.company.bank.application.usecase.PerformTransferUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
@Tag(name = "Transfers", description = "Endpoints for executing bank transfers")
public class TransferController {

    private final PerformTransferUseCase performTransferUseCase;

    public TransferController(PerformTransferUseCase performTransferUseCase) {
        this.performTransferUseCase = performTransferUseCase;
    }

    /**
     * Realiza una nueva transferencia entre cuentas.
     *
     * @param requestDto datos de la transferencia.
     * @return 201 Created con el registro de la transferencia.
     */
    @PostMapping
    @Operation(summary = "Perform a transfer", description = "Transfers money from source account to destination account.")
    public ResponseEntity<TransferDto> performTransfer(@Valid @RequestBody TransferRequestDto requestDto) {
        TransferDto transferDto = performTransferUseCase.performTransfer(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferDto);
    }
}
