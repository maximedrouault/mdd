package org.mdd.mddapi.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @GetMapping("/comments/post/{postId}")
    public ResponseEntity<Set<CommentDto>> getCommentsByPostId(@PathVariable @Positive Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }
}
