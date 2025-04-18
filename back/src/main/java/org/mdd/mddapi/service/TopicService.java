package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.topic.response.SubscribedTopicDto;
import org.mdd.mddapi.entity.Topic;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.TopicNotFoundException;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.TopicMapper;
import org.mdd.mddapi.repository.TopicRepository;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service class for managing topics.
 */
@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final TopicMapper topicMapper;

    /**
     * Retrieves all topics.
     *
     * @return a list of SubscribedTopicDto objects
     */
    public List<SubscribedTopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        return topicMapper.toSubscribedTopicDto(topics);
    }

    /**
     * Retrieves all topics subscribed to by a specific user.
     *
     * @param userId the ID of the user
     * @return a set of SubscribedTopicDto objects
     */
    public Set<SubscribedTopicDto> getSubscribedTopics(Long userId) {
        Set<Topic> subscribedTopics = topicRepository.findByUsers_Id(userId);

        return topicMapper.toSubscribedTopicDto(subscribedTopics);
    }

    /**
     * Subscribes a user to a specific topic.
     *
     * @param topicId the ID of the topic
     * @param userId the ID of the user
     */
    public void saveTopicSubscription(Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        topic.getUsers().add(user);

        topicRepository.save(topic);
    }

    /**
     * Unsubscribes a user from a specific topic.
     *
     * @param topicId the ID of the topic
     * @param userId the ID of the user
     */
    public void deleteTopicSubscription(Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        topic.getUsers().remove(user);

        topicRepository.save(topic);
    }
}
