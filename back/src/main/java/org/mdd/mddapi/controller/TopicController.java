package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.topic.TopicDto;
import org.mdd.mddapi.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;


    @GetMapping("/topics")
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/topics/subscribed/{userId}")
    public ResponseEntity<Set<TopicDto>> getSubscribedTopics(@PathVariable @Positive Long userId) {
        return ResponseEntity.ok(topicService.getSubscribedTopics(userId));
    }

    @PostMapping("/topics/{topicId}/subscribed/{userId}")
    public ResponseEntity<Void> saveTopicSubscription(@PathVariable @Positive Long topicId, @PathVariable @Positive Long userId) {
        topicService.saveTopicSubscription(topicId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/topics/{topicId}/subscribed/{userId}")
    public ResponseEntity<Void> deleteTopicSubscription(@PathVariable @Positive Long topicId, @PathVariable @Positive Long userId) {
        topicService.deleteTopicSubscription(topicId, userId);

        return ResponseEntity.noContent().build();
    }
}