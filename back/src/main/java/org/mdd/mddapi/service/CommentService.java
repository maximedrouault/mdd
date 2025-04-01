package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.request.comment.CommentPayloadDto;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.entity.Comment;
import org.mdd.mddapi.entity.Post;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.PostNotFoundException;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.CommentMapper;
import org.mdd.mddapi.repository.CommentRepository;
import org.mdd.mddapi.repository.PostRepository;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;


    public Set<CommentDto> getCommentsByPostId(Long postId) {
        Set<Comment> comments = commentRepository.findCommentsByPost_Id(postId);

        return commentMapper.toDtos(comments);
    }

    public void saveComment(CommentPayloadDto commentPayloadDto) {
        Long postId = commentPayloadDto.postId();
        Long authorId = commentPayloadDto.authorId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        User author = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));

        Comment comment = Comment.builder()
                .content(commentPayloadDto.content())
                .author(author)
                .post(post)
                .build();

        commentRepository.save(comment);
    }
}
