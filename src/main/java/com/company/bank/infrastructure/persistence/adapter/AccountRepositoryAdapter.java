package com.company.bank.infrastructure.persistence.adapter;

import com.company.bank.domain.entity.Account;
import com.company.bank.domain.repository.AccountRepository;
import com.company.bank.infrastructure.persistence.entity.AccountJpaEntity;
import com.company.bank.infrastructure.persistence.mapper.AccountJpaMapper;
import com.company.bank.infrastructure.persistence.repository.SpringDataAccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador que implementa el puerto del dominio usando Spring Data JPA.
 */
@Component
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository springDataRepository;
    private final AccountJpaMapper mapper;

    public AccountRepositoryAdapter(SpringDataAccountRepository springDataRepository, AccountJpaMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return springDataRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Account> findByIdForUpdate(Long id) {
        return springDataRepository.findByIdForUpdate(id).map(mapper::toDomain);
    }

    @Override
    public Account save(Account account) {
        AccountJpaEntity entity = mapper.toEntity(account);
        AccountJpaEntity savedEntity = springDataRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
