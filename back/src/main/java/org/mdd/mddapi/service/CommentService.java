package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.mapper.CommentMapper;
import org.mdd.mddapi.entity.Comment;
import org.mdd.mddapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    public Set<CommentDto> getCommentsByPostId(Long postId) {
        Set<Comment> comments = commentRepository.findCommentsByPost_Id(postId);

        return commentMapper.toDtos(comments);
    }
}
