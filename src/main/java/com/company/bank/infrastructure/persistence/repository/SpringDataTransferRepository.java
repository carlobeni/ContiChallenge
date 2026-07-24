package com.company.bank.infrastructure.persistence.repository;

import com.company.bank.infrastructure.persistence.entity.TransferJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataTransferRepository extends JpaRepository<TransferJpaEntity, Long> {
    
    @Query("SELECT t FROM TransferJpaEntity t WHERE t.sourceAccountId = :accountId OR t.destinationAccountId = :accountId ORDER BY t.createdAt DESC")
    List<TransferJpaEntity> findByAccountId(@Param("accountId") Long accountId);
}
