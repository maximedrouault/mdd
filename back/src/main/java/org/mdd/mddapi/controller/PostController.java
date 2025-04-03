package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.request.post.PostPayloadDto;
import org.mdd.mddapi.dto.response.post.PostDetailsDto;
import org.mdd.mddapi.dto.response.post.SubscribedPostDto;
import org.mdd.mddapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/posts/subscribed/{userId}")
    public ResponseEntity<Set<SubscribedPostDto>> getSubscribedPosts(@PathVariable @Positive Long userId) {
        return ResponseEntity.ok(postService.getSubscribedPosts(userId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailsDto> getPostDetails(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(postService.getPostDetails(postId));
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> savePost(@RequestBody @Valid PostPayloadDto postPayloadDto) {
        postService.savePost(postPayloadDto);

        return ResponseEntity.noContent().build();
    }
}
