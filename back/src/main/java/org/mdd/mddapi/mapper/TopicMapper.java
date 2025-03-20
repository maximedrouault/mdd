package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.TopicDto;
import org.mdd.mddapi.entity.Topic;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    TopicDto toDto(Topic topic);

    List<TopicDto> toDtos(List<Topic> topics);
}