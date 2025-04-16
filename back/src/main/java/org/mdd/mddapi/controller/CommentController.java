package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.comment.request.CommentPayloadDto;
import org.mdd.mddapi.dto.comment.response.CommentDto;
import org.mdd.mddapi.service.AuthService;
import org.mdd.mddapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller class for managing comments.
 * Provides endpoints for retrieving and saving comments related to posts.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final AuthService authService;


    /**
    * Retrieves all comments associated with a specific post.
    *
    * @param postId the ID of the post for which comments are to be retrieved. Must be positive.
    * @return a set of {@link CommentDto} objects representing the comments.
    */
    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<Set<CommentDto>> getCommentsByPostId(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    /**
    * Saves a new comment for a post.
    *
    * @param authToken the JWT token of the authenticated user.
    * @param commentPayloadDto the payload containing the details of the comment to be saved.
    * @return void. The HTTP status 204 (No Content) is returned if the comment is successfully saved.
    */
    @PostMapping("/comments/post")
    public ResponseEntity<Void> saveComment(@AuthenticationPrincipal @NotNull Jwt authToken,
                                            @RequestBody @Valid @NotNull CommentPayloadDto commentPayloadDto) {
        Long authorId = authService.getUserIdFromToken(authToken);

        commentService.saveComment(commentPayloadDto, authorId);

        return ResponseEntity.noContent().build();
    }
}
