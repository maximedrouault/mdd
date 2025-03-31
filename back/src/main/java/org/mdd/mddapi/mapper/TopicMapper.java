package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.topic.TopicDto;
import org.mdd.mddapi.entity.Topic;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    TopicDto toDto(Topic topic);

    List<TopicDto> toDtoList(List<Topic> topics);

    Set<TopicDto> toDtoSet(Set<Topic> subscribedTopics);
}