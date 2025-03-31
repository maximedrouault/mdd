package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.post.PostDetailsDto;
import org.mdd.mddapi.entity.Post;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostDetailsMapper {

    @Mapping(source = "topicName", target = "topic.name")
    @Mapping(source = "authorName", target = "author.username")
    Post toEntity(PostDetailsDto postDetailsDto);

    @InheritInverseConfiguration(name = "toEntity")
    PostDetailsDto toDto(Post post);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDetailsDto postDetailsDto, @MappingTarget Post post);
}