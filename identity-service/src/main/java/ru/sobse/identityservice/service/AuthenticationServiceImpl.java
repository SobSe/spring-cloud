package ru.sobse.identityservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sobse.identityservice.dto.AuthenticationRequestDto;
import ru.sobse.identityservice.dto.AuthenticationUserDto;
import ru.sobse.identityservice.entity.User;
import ru.sobse.identityservice.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationUserDto findByEmailAndPassword(AuthenticationRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.email());
        if (user != null && passwordEncoder.matches(requestDto.password(), user.getPassword())) {
            return new AuthenticationUserDto(user.getEmail());
        } else {
            return null;
        }
    }
}
