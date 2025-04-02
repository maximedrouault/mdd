package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.post.PostDetailsDto;
import org.mdd.mddapi.dto.response.post.SubscribedPostDto;
import org.mdd.mddapi.entity.Post;
import org.mdd.mddapi.exception.PostNotFoundException;
import org.mdd.mddapi.mapper.PostMapper;
import org.mdd.mddapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;


    public Set<SubscribedPostDto> getSubscribedPosts(Long userId) {
        Set<Post> subscribedPosts = postRepository.findByTopic_Users_Id(userId);

        return postMapper.toSubscribedPostDto(subscribedPosts);
    }

    public PostDetailsDto getPostDetails(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        return postMapper.toPostDetailsDto(post);
    }
}
