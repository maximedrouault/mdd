package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.topic.response.SubscribedTopicDto;
import org.mdd.mddapi.entity.Topic;

import java.util.List;
import java.util.Set;

/**
 * Mapper interface for converting between Topic entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    /**
     * Converts a Topic entity to a SubscribedTopicDto.
     *
     * @param topic the Topic entity to convert
     * @return the converted SubscribedTopicDto
     */
    SubscribedTopicDto toSubscribedTopicDto(Topic topic);

    /**
     * Converts a list of Topic entities to a list of SubscribedTopicDto.
     *
     * @param topics the list of Topic entities to convert
     * @return the converted list of SubscribedTopicDto
     */
    List<SubscribedTopicDto> toSubscribedTopicDto(List<Topic> topics);

    /**
     * Converts a set of Topic entities to a set of SubscribedTopicDto.
     *
     * @param subscribedTopics the set of Topic entities to convert
     * @return the converted set of SubscribedTopicDto
     */
    Set<SubscribedTopicDto> toSubscribedTopicDto(Set<Topic> subscribedTopics);
}
