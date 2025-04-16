package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

/**
 * Controller class for handling authentication-related operations.
 * Provides endpoints for login, registration, and user updates.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    /**
     * Endpoint for user login.
     *
     * @param loginPayloadDto the login payload containing user credentials.
     * @return an {@link AuthTokenDto} containing the authentication token.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<AuthTokenDto> getAuthToken(@Valid @RequestBody @NotNull LoginPayloadDto loginPayloadDto) {
        return ResponseEntity.ok(authService.getAuthToken(loginPayloadDto));
    }

    /**
     * Endpoint for user registration.
     *
     * @param registerPayloadDto the registration payload containing user details.
     * @return void. The HTTP status 201 (Created) is returned if the registration is successful.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody @NotNull RegisterPayloadDto registerPayloadDto) {
        authService.registerUser(registerPayloadDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint for updating user information.
     *
     * @param authToken the JWT token of the authenticated user.
     * @param updatePayloadDto the update payload containing new user details.
     * @return void. The HTTP status 200 (OK) is returned if the update is successful.
     */
    @PutMapping("/auth/update")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal @NotNull Jwt authToken,
                                           @Valid @RequestBody @NotNull UpdatePayloadDto updatePayloadDto) {
        Long userId = authService.getUserIdFromToken(authToken);

        authService.updateUser(updatePayloadDto, userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}