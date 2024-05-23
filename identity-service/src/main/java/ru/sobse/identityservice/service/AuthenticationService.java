package ru.sobse.identityservice.service;

import ru.sobse.identityservice.dto.AuthenticationRequestDto;
import ru.sobse.identityservice.dto.AuthenticationUserDto;

public interface AuthenticationService {

    AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto);
}
