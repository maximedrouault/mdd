package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.entity.Comment;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(source = "authorName", target = "author.username")
    Comment toEntity(CommentDto commentDto);

    @Mapping(source = "author.username", target = "authorName")
    CommentDto toDto(Comment comment);

    Set<CommentDto> toDtos(Set<Comment> comments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "authorName", target = "author.username")
    Comment partialUpdate(CommentDto commentDto, @MappingTarget Comment comment);
}