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

/**
 * Controller class for managing posts.
 * Provides endpoints for retrieving subscribed posts, retrieving post details, and saving new posts.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AuthService authService;



    /**
     * Retrieves all posts the authenticated user is subscribed to.
     *
     * @param authToken the JWT token of the authenticated user.
     * @return a set of {@link SubscribedPostDto} objects representing the subscribed posts.
     */
    @GetMapping("/posts/subscribed")
    public ResponseEntity<Set<SubscribedPostDto>> getSubscribedPosts(@AuthenticationPrincipal @NotNull Jwt authToken) {
        Long userId = authService.getUserIdFromToken(authToken);

        return ResponseEntity.ok(postService.getSubscribedPosts(userId));
    }

    /**
     * Retrieves the details of a specific post.
     *
     * @param postId the ID of the post to retrieve. Must be positive.
     * @return a {@link PostDetailsDto} object containing the details of the post.
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailsDto> getPostDetails(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(postService.getPostDetails(postId));
    }

    /**
     * Saves a new post.
     *
     * @param authToken the JWT token of the authenticated user.
     * @param postPayloadDto the payload containing the details of the post to be saved.
     * @return void.
     */
    @PostMapping("/posts")
    public ResponseEntity<Void> savePost(@AuthenticationPrincipal @NotNull Jwt authToken,
                                         @RequestBody @Valid @NotNull PostPayloadDto postPayloadDto) {
        Long authorId = authService.getUserIdFromToken(authToken);

        postService.savePost(postPayloadDto, authorId);

        return ResponseEntity.noContent().build();
    }
}
