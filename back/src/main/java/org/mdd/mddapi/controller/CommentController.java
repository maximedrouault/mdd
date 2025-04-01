package org.mdd.mddapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.request.comment.CommentPayloadDto;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/comments/post")
    public ResponseEntity<Void> saveComment(@RequestBody @Valid CommentPayloadDto commentPayloadDto) {
        commentService.saveComment(commentPayloadDto);

        return ResponseEntity.noContent().build();
    }
}
