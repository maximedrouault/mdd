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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final AuthService authService;


    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<Set<CommentDto>> getCommentsByPostId(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @PostMapping("/comments/post")
    public ResponseEntity<Void> saveComment(@AuthenticationPrincipal @NotNull Jwt authToken,
                                            @RequestBody @Valid @NotNull CommentPayloadDto commentPayloadDto) {
        Long authorId = authService.getUserIdFromToken(authToken);

        commentService.saveComment(commentPayloadDto, authorId);

        return ResponseEntity.noContent().build();
    }
}
