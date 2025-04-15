package org.mdd.mddapi.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.topic.response.SubscribedTopicDto;
import org.mdd.mddapi.service.AuthService;
import org.mdd.mddapi.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final AuthService authService;


    @GetMapping("/topics")
    public ResponseEntity<List<SubscribedTopicDto>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/topics/subscribed")
    public ResponseEntity<Set<SubscribedTopicDto>> getSubscribedTopics(@AuthenticationPrincipal @NotNull Jwt authToken) {
        Long userId = authService.getUserIdFromToken(authToken);

        return ResponseEntity.ok(topicService.getSubscribedTopics(userId));
    }

    @PostMapping("/topics/{topicId}/subscribed")
    public ResponseEntity<Void> saveTopicSubscription(@AuthenticationPrincipal @NotNull Jwt authToken,
                                                      @PathVariable @Positive Long topicId) {
        Long userId = authService.getUserIdFromToken(authToken);

        topicService.saveTopicSubscription(topicId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/topics/{topicId}/subscribed")
    public ResponseEntity<Void> deleteTopicSubscription(@AuthenticationPrincipal @NotNull Jwt authToken,
                                                        @PathVariable @Positive Long topicId) {
        Long userId = authService.getUserIdFromToken(authToken);

        topicService.deleteTopicSubscription(topicId, userId);

        return ResponseEntity.noContent().build();
    }
}