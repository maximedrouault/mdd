package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.PostDto;
import org.mdd.mddapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/user/{userId}/subscribed-topics/{topicId}")
    public ResponseEntity<Void> saveTopicSubscription(@PathVariable @Positive Long userId, @PathVariable @Positive Long topicId) {
        userService.saveTopicSubscription(userId, topicId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/user/{userId}/subscribed-topics/{topicId}")
    public ResponseEntity<Void> deleteTopicSubscription(@PathVariable @Positive Long userId, @PathVariable @Positive Long topicId) {
        userService.deleteTopicSubscription(userId, topicId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}/subscribed-posts")
    public ResponseEntity<Set<PostDto>> getSubscribedPosts(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(userService.getSubscribedPosts(id));
    }
}
