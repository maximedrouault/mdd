package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.TopicDto;
import org.mdd.mddapi.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}