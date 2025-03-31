package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.post.PostDetailsDto;
import org.mdd.mddapi.dto.response.post.PostDto;
import org.mdd.mddapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts/subscribed/{userId}")
    public ResponseEntity<Set<PostDto>> getSubscribedPosts(@PathVariable @Positive Long userId) {
        return ResponseEntity.ok(postService.getSubscribedPosts(userId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailsDto> getPostDetails(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(postService.getPostDetails(postId));
    }
}
