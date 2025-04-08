package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.comment.request.CommentPayloadDto;
import org.mdd.mddapi.dto.comment.response.CommentDto;
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
        Set<Comment> comments = commentRepository.findByPost_Id(postId);

        return commentMapper.toDto(comments);
    }

    public void saveComment(CommentPayloadDto commentPayloadDto) {
        Long postId = commentPayloadDto.postId();
        Long authorId = commentPayloadDto.authorId();
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        User foundUser = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));

        Comment commentToAdd = commentMapper.toEntity(commentPayloadDto);
        commentToAdd.setPost(foundPost);
        commentToAdd.setAuthor(foundUser);

        commentRepository.save(commentToAdd);
    }
}
