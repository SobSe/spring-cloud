package ru.sobse.partnerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDto implements Serializable {
    private Long id;
    private String name;
}
