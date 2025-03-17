package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.TopicDto;
import org.mdd.mddapi.entity.Topic;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {
    Topic toEntity(TopicDto topicDto);

    TopicDto toDto(Topic topic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Topic partialUpdate(TopicDto topicDto, @MappingTarget Topic topic);
}