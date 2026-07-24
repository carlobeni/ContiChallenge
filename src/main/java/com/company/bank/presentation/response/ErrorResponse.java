package com.company.bank.presentation.response;

import java.time.LocalDateTime;

/**
 * Modelo de respuesta estandarizada para errores en la API.
 */
public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {}
