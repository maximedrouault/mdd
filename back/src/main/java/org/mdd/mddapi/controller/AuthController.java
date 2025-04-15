package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.auth.request.LoginPayloadDto;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.dto.auth.request.UpdatePayloadDto;
import org.mdd.mddapi.dto.auth.response.AuthTokenDto;
import org.mdd.mddapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterPayloadDto registerPayloadDto) {
        authService.registerUser(registerPayloadDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/auth/update")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal Jwt authToken,
                                           @Valid @RequestBody UpdatePayloadDto updatePayloadDto) {
        Long userId = authService.getUserIdFromToken(authToken);

        authService.updateUser(updatePayloadDto, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}