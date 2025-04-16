package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.post.request.PostPayloadDto;
import org.mdd.mddapi.dto.post.response.PostDetailsDto;
import org.mdd.mddapi.dto.post.response.SubscribedPostDto;
import org.mdd.mddapi.entity.Post;
import org.mdd.mddapi.entity.Topic;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.PostNotFoundException;
import org.mdd.mddapi.exception.TopicNotFoundException;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.PostMapper;
import org.mdd.mddapi.repository.PostRepository;
import org.mdd.mddapi.repository.TopicRepository;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final PostMapper postMapper;


    public Set<SubscribedPostDto> getSubscribedPosts(Long userId) {
        Set<Post> subscribedPosts = postRepository.findByTopic_Users_Id(userId);

        return postMapper.toSubscribedPostDto(subscribedPosts);
    }

    public PostDetailsDto getPostDetails(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        return postMapper.toPostDetailsDto(post);
    }

    public void savePost(PostPayloadDto postPayloadDto, Long authorId) {
        Long topicId = postPayloadDto.topicId();
        User foundUser = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));
        Topic foundTopic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));

        Post postToAdd = postMapper.toEntity(postPayloadDto);
        postToAdd.setAuthor(foundUser);
        postToAdd.setTopic(foundTopic);

        postRepository.save(postToAdd);
    }
}
