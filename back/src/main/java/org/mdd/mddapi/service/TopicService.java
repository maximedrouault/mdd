package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.response.TopicDto;
import org.mdd.mddapi.entity.Topic;
import org.mdd.mddapi.mapper.TopicMapper;
import org.mdd.mddapi.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;


    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        return topicMapper.toDtos(topics);
    }
}