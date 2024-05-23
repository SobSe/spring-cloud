package ru.sobse.identityservice.dto;

import java.io.Serializable;

public record AuthenticationRequestDto(
        String email,
        String password) implements Serializable {
}
