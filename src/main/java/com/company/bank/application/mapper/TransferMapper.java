package com.company.bank.application.mapper;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.domain.entity.Transfer;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    TransferDto toDto(Transfer transfer);
    List<TransferDto> toDtoList(List<Transfer> transfers);
}
