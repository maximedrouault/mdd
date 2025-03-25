package org.mdd.mddapi.controller;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.TopicDto;
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


    @GetMapping("/user/{id}/subscribed-topics")
    public ResponseEntity<Set<TopicDto>> getSubscribedTopics(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getSubscribedTopics(id));
    }

    @PostMapping("/user/{userId}/subscribed-topics/{topicId}")
    public ResponseEntity<Void> saveTopicSubscription(@PathVariable Long userId, @PathVariable Long topicId) {
        userService.saveTopicSubscription(userId, topicId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
