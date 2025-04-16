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

/**
 * Controller class for managing topics.
 * Provides endpoints for retrieving all topics, retrieving subscribed topics, subscribing to a topic, and unsubscribing from a topic.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final AuthService authService;


    /**
    * Retrieves all topics.
    *
    * @return a list of {@link SubscribedTopicDto} objects representing all topics.
    */
    @GetMapping("/topics")
    public ResponseEntity<List<SubscribedTopicDto>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    /**
    * Retrieves all topics the authenticated user is subscribed to.
    *
    * @param authToken the JWT token of the authenticated user.
    * @return a set of {@link SubscribedTopicDto} objects representing the subscribed topics.
    */
    @GetMapping("/topics/subscribed")
    public ResponseEntity<Set<SubscribedTopicDto>> getSubscribedTopics(@AuthenticationPrincipal @NotNull Jwt authToken) {
        Long userId = authService.getUserIdFromToken(authToken);

        return ResponseEntity.ok(topicService.getSubscribedTopics(userId));
    }

    /**
    * Subscribes the authenticated user to a specific topic.
    *
    * @param authToken the JWT token of the authenticated user.
    * @param topicId the ID of the topic to subscribe to. Must be positive.
    * @return void.
    */
    @PostMapping("/topics/{topicId}/subscribed")
    public ResponseEntity<Void> saveTopicSubscription(@AuthenticationPrincipal @NotNull Jwt authToken,
                                                      @PathVariable @Positive Long topicId) {
        Long userId = authService.getUserIdFromToken(authToken);

        topicService.saveTopicSubscription(topicId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
    * Unsubscribes the authenticated user from a specific topic.
    *
    * @param authToken the JWT token of the authenticated user.
    * @param topicId the ID of the topic to unsubscribe from. Must be positive.
    * @return void.
    */
    @DeleteMapping("/topics/{topicId}/subscribed")
    public ResponseEntity<Void> deleteTopicSubscription(@AuthenticationPrincipal @NotNull Jwt authToken,
                                                        @PathVariable @Positive Long topicId) {
        Long userId = authService.getUserIdFromToken(authToken);

        topicService.deleteTopicSubscription(topicId, userId);

        return ResponseEntity.noContent().build();
    }
}