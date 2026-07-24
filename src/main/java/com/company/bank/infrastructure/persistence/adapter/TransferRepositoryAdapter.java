package com.company.bank.infrastructure.persistence.adapter;

import com.company.bank.domain.entity.Transfer;
import com.company.bank.domain.repository.TransferRepository;
import com.company.bank.infrastructure.persistence.entity.TransferJpaEntity;
import com.company.bank.infrastructure.persistence.mapper.TransferJpaMapper;
import com.company.bank.infrastructure.persistence.repository.SpringDataTransferRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Adaptador que implementa el puerto del dominio usando Spring Data JPA.
 */
@Component
public class TransferRepositoryAdapter implements TransferRepository {

    private final SpringDataTransferRepository springDataRepository;
    private final TransferJpaMapper mapper;

    public TransferRepositoryAdapter(SpringDataTransferRepository springDataRepository, TransferJpaMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Transfer save(Transfer transfer) {
        TransferJpaEntity entity = mapper.toEntity(transfer);
        TransferJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Transfer> findByAccountId(Long accountId) {
        List<TransferJpaEntity> entities = springDataRepository.findByAccountId(accountId);
        return mapper.toDomainList(entities);
    }
}
