package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.request.comment.CommentPayloadDto;
import org.mdd.mddapi.entity.Comment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentPayloadMapper {

    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "authorId", target = "author.id")
    Comment toEntity(CommentPayloadDto commentPayloadDto);

    @InheritInverseConfiguration(name = "toEntity")
    CommentPayloadDto toDto(Comment comment);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentPayloadDto commentPayloadDto, @MappingTarget Comment comment);
}