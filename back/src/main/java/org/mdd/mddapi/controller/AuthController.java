package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.auth.request.LoginDto;
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
    public ResponseEntity<AuthTokenDto> getAuthToken(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.getAuthToken(loginDto));
    }
}
