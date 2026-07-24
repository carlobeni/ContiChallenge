package com.company.bank.infrastructure.persistence.mapper;

import com.company.bank.domain.entity.Transfer;
import com.company.bank.infrastructure.persistence.entity.TransferJpaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferJpaMapper {
    Transfer toDomain(TransferJpaEntity entity);
    TransferJpaEntity toEntity(Transfer domain);
    List<Transfer> toDomainList(List<TransferJpaEntity> entities);
}
