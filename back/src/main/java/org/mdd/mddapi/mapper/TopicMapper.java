package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.topic.response.SubscribedTopicDto;
import org.mdd.mddapi.entity.Topic;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    SubscribedTopicDto toSubscribedTopicDto(Topic topic);

    List<SubscribedTopicDto> toSubscribedTopicDto(List<Topic> topics);

    Set<SubscribedTopicDto> toSubscribedTopicDto(Set<Topic> subscribedTopics);
}