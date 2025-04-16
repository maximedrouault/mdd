package org.mdd.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mdd.mddapi.dto.comment.request.CommentPayloadDto;
import org.mdd.mddapi.dto.comment.response.CommentDto;
import org.mdd.mddapi.entity.Comment;

import java.util.Set;

/**
 * Mapper interface for converting between Comment entity, CommentDto, and CommentPayloadDto.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    /**
     * Maps a Comment entity to a CommentDto.
     *
     * @param comment the Comment entity to map
     * @return the mapped CommentDto
     */
    @Mapping(source = "author.username", target = "authorName")
    CommentDto toDto(Comment comment);

    /**
     * Maps a set of Comment entities to a set of CommentDto objects.
     *
     * @param comments the set of Comment entities to map
     * @return the mapped set of CommentDto objects
     */
    Set<CommentDto> toDto(Set<Comment> comments);

    /**
     * Maps a CommentPayloadDto to a Comment entity.
     *
     * @param commentPayloadDto the CommentPayloadDto to map
     * @return the mapped Comment entity
     */
    @Mapping(source = "postId", target = "post.id")
    Comment toEntity(CommentPayloadDto commentPayloadDto);
}
