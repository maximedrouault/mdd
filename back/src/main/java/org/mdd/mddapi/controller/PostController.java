package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.post.request.PostPayloadDto;
import org.mdd.mddapi.dto.post.response.PostDetailsDto;
import org.mdd.mddapi.dto.post.response.SubscribedPostDto;
import org.mdd.mddapi.service.AuthService;
import org.mdd.mddapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AuthService authService;


    @GetMapping("/posts/subscribed")
    public ResponseEntity<Set<SubscribedPostDto>> getSubscribedPosts(@AuthenticationPrincipal @NotNull Jwt authToken) {
        Long userId = authService.getUserIdFromToken(authToken);

        return ResponseEntity.ok(postService.getSubscribedPosts(userId));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailsDto> getPostDetails(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(postService.getPostDetails(postId));
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> savePost(@AuthenticationPrincipal @NotNull Jwt authToken,
                                         @RequestBody @Valid @NotNull PostPayloadDto postPayloadDto) {
        Long authorId = authService.getUserIdFromToken(authToken);

        postService.savePost(postPayloadDto, authorId);

        return ResponseEntity.noContent().build();
    }
}
