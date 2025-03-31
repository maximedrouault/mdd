package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.TopicDto;
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

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final TopicMapper topicMapper;


    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        return topicMapper.toDtoList(topics);
    }

    public Set<TopicDto> getSubscribedTopics(Long userId) {
        Set<Topic> subscribedTopics = topicRepository.findByUsers_Id(userId);

        return topicMapper.toDtoSet(subscribedTopics);
    }

    public void saveTopicSubscription(Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        topic.getUsers().add(user);

        topicRepository.save(topic);
    }

    public void deleteTopicSubscription(Long topicId, Long userId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException(topicId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        topic.getUsers().remove(user);

        topicRepository.save(topic);
    }
}