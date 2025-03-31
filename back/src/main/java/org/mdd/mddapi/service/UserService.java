package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.PostDto;
import org.mdd.mddapi.entity.Post;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.PostMapper;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostMapper postMapper;


    // TODO : Move this method to PostService
    public Set<PostDto> getSubscribedPosts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Set<Post> subscribedPosts = user.getSubscribedTopics().stream()
                .flatMap(topic -> topic.getPosts().stream())
                .collect(Collectors.toSet());

        return postMapper.toDtoSet(subscribedPosts);
    }
}
