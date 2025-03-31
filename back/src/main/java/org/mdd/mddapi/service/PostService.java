package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.PostDto;
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


    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        return postMapper.toDto(post);
    }

    public Set<PostDto> getSubscribedPosts(Long userId) {
        Set<Post> subscribedPosts = postRepository.findByTopic_Users_Id(userId);

        return postMapper.toDtoSet(subscribedPosts);
    }
}
