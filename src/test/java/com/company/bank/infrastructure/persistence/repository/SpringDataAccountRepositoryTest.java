package com.company.bank.infrastructure.persistence.repository;

import com.company.bank.AbstractIntegrationTest;
import com.company.bank.infrastructure.persistence.entity.AccountJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringDataAccountRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private SpringDataAccountRepository accountRepository;

    @Test
    void shouldFindAccountById() {
        // Flyway migration already inserted IDs 1 and 2
        Optional<AccountJpaEntity> account = accountRepository.findById(1L);
        assertTrue(account.isPresent());
        assertEquals("Alice", account.get().getOwnerName());
    }
}
