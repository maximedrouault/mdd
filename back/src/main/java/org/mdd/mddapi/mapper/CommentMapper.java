package org.mdd.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mdd.mddapi.dto.request.comment.CommentPayloadDto;
import org.mdd.mddapi.dto.response.comment.CommentDto;
import org.mdd.mddapi.entity.Comment;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    // Handle the mapping between CommentDto and Comment entity
    @Mapping(source = "author.username", target = "authorName")
    CommentDto toDto(Comment comment);

    Set<CommentDto> toDto(Set<Comment> comments);


    // Handle the mapping between CommentPayloadDto and Comment entity
    @Mapping(source = "postId", target = "post.id")
    @Mapping(source = "authorId", target = "author.id")
    Comment toEntity(CommentPayloadDto commentPayloadDto);
}