package com.company.bank.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa una transferencia de dinero entre dos cuentas.
 * 
 * Es una entidad inmutable en su núcleo de negocio tras ser ejecutada,
 * ya que registra un evento histórico que no debe ser modificado.
 */
public class Transfer {
    private Long id;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public Transfer() {}

    public Transfer(Long id, Long sourceAccountId, Long destinationAccountId, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSourceAccountId() { return sourceAccountId; }
    public void setSourceAccountId(Long sourceAccountId) { this.sourceAccountId = sourceAccountId; }
    public Long getDestinationAccountId() { return destinationAccountId; }
    public void setDestinationAccountId(Long destinationAccountId) { this.destinationAccountId = destinationAccountId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
