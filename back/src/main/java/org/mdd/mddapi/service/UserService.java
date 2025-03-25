package org.mdd.mddapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.TopicDto;
import org.mdd.mddapi.entity.Topic;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.mapper.TopicMapper;
import org.mdd.mddapi.repository.TopicRepository;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;


    public Set<TopicDto> getSubscribedTopics(Long userId) {
        Set<Topic> subscribedTopics = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new) // TODO: implement custom exception handling
                .getSubscribedTopics();

        return topicMapper.toDtoSet(subscribedTopics);
    }

    public void saveTopicSubscription(Long userId, Long topicId) {
        User foundUser = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new); // TODO: implement custom exception handling
        Topic foundTopic = topicRepository.findById(topicId).orElseThrow(EntityNotFoundException::new); // TODO: implement custom exception handling

        foundUser.getSubscribedTopics().add(foundTopic);

        userRepository.save(foundUser);
    }
}
