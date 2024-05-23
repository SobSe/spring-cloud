package ru.sobse.identityservice.dto;

import java.io.Serializable;
import java.util.Set;

public record AuthenticationUserDto(String email) implements Serializable {
}