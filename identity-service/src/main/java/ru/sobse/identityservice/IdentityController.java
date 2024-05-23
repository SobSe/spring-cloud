package ru.sobse.identityservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobse.identityservice.dto.AuthenticationRequestDto;
import ru.sobse.identityservice.dto.AuthenticationUserDto;
import ru.sobse.identityservice.exception.BadRequestException;
import ru.sobse.identityservice.security.JwtTokenProvider;
import ru.sobse.identityservice.service.AuthenticationServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class IdentityController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationUserDto user = authenticationService.findByEmailAndPassword(requestDto);
        if (user == null) {
            throw new BadRequestException("Wrong email or password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("username", user.email());
        response.put("token", jwtTokenProvider.createToken(user));
        return ResponseEntity.ok(response);
    }
}
