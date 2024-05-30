package ru.sobse.partnerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contracts_response")
public class ContractResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    private Long cr_id;

    @Column
    private UUID uuid;

    @Column
    private long id;

    @Column
    private String name;

    @Column
    private boolean complete;
}
