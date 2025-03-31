package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.PostDto;
import org.mdd.mddapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // TODO : Move this to the PostController
    @GetMapping("/user/{id}/subscribed-posts")
    public ResponseEntity<Set<PostDto>> getSubscribedPosts(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(userService.getSubscribedPosts(id));
    }
}
