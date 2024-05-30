package ru.sobse.partnerservice.mapper;

import org.mapstruct.*;
import ru.sobse.partnerservice.dto.ContractResponseDto;
import ru.sobse.partnerservice.entity.ContractResponse;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface ContractResponseMapper {

    @Mapping(target = "uuid", source = "uuid")
    ContractResponse toEntity(ContractResponseDto contractResponse, UUID uuid);

    ContractResponseDto toDto(ContractResponse contractResponse);

    /*@Mapping(target = "uuid", source = "uuid")
    List<ContractResponse> toEntityList(List<ContractResponseDto> contractResponseList, @Context UUID uuid);

    List<ContractResponseDto> toDtoList(List<ContractResponse> contractResponseList);*/
}
