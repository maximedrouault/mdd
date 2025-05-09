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

/**
 * Service class for managing comments.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /**
     * Retrieves all comments associated with a specific post.
     *
     * @param postId the ID of the post
     * @return a set of CommentDto objects
     */
    public Set<CommentDto> getCommentsByPostId(Long postId) {
        Set<Comment> comments = commentRepository.findByPost_Id(postId);

        return commentMapper.toDto(comments);
    }

    /**
     * Saves a new comment for a specific post and author.
     *
     * @param commentPayloadDto the payload containing comment details
     * @param authorId the ID of the author
     */
    public void saveComment(CommentPayloadDto commentPayloadDto, Long authorId) {
        Long postId = commentPayloadDto.postId();
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
        User foundUser = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));

        Comment commentToAdd = commentMapper.toEntity(commentPayloadDto);
        commentToAdd.setPost(foundPost);
        commentToAdd.setAuthor(foundUser);

        commentRepository.save(commentToAdd);
    }
}
