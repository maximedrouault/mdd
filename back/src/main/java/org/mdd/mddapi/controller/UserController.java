package org.mdd.mddapi.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.mdd.mddapi.service.AuthService;
import org.mdd.mddapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing user information.
 * Provides an endpoint for retrieving the authenticated user's information.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;


    /**
     * Retrieves the authenticated user's information.
     *
     * @param authToken the JWT token of the authenticated user.
     * @return a {@link UserDto} object containing the user's information.
     */
    @GetMapping("/users")
    public ResponseEntity<UserDto> getUserInfos(@AuthenticationPrincipal @NotNull Jwt authToken) {
        Long userId = authService.getUserIdFromToken(authToken);

        return ResponseEntity.ok(userService.getUserInfos(userId));
    }
}
