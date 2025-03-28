package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.PostDto;
import org.mdd.mddapi.entity.Post;
import org.mdd.mddapi.entity.Topic;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.TopicNotFoundException;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.PostMapper;
import org.mdd.mddapi.repository.TopicRepository;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final PostMapper postMapper;


    // TODO : Move this method to TopicService
    public void deleteTopicSubscription(Long userId, Long topicId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));

        user.getSubscribedTopics().remove(topic);

        userRepository.save(user);
    }

    // TODO : Move this method to PostService
    public Set<PostDto> getSubscribedPosts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Set<Post> subscribedPosts = user.getSubscribedTopics().stream()
                .flatMap(topic -> topic.getPosts().stream())
                .collect(Collectors.toSet());

        return postMapper.toDtoSet(subscribedPosts);
    }
}
