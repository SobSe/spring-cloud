package ru.sobse.users.dto;

import java.io.Serializable;

public record ContractResponseDto(
        Long id,
        String name) implements Serializable {
}
