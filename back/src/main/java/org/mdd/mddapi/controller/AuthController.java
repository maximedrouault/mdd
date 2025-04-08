package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.auth.request.LoginPayloadDto;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.dto.auth.response.AuthTokenDto;
import org.mdd.mddapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/auth/login")
    public ResponseEntity<AuthTokenDto> getAuthToken(@Valid @RequestBody LoginPayloadDto loginPayloadDto) {
        return ResponseEntity.ok(authService.getAuthToken(loginPayloadDto));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthTokenDto> registerUser(@Valid @RequestBody RegisterPayloadDto registerPayloadDto) {
        return ResponseEntity.ok(authService.registerUser(registerPayloadDto));
    }
}
