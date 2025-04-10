package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.mdd.mddapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@Positive @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
