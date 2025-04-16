package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.post.request.PostPayloadDto;
import org.mdd.mddapi.dto.post.response.PostDetailsDto;
import org.mdd.mddapi.dto.post.response.SubscribedPostDto;
import org.mdd.mddapi.entity.Post;

import java.util.Set;

/**
 * Mapper interface for converting between Post entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    /**
     * Converts a Post entity to a SubscribedPostDto.
     *
     * @param post the Post entity to convert
     * @return the converted SubscribedPostDto
     */
    @Mapping(source = "author.username", target = "authorName")
    SubscribedPostDto toSubscribedPostDto(Post post);

    /**
     * Converts a set of Post entities to a set of SubscribedPostDto.
     *
     * @param posts the set of Post entities to convert
     * @return the converted set of SubscribedPostDto
     */
    Set<SubscribedPostDto> toSubscribedPostDto(Set<Post> posts);

    /**
     * Converts a Post entity to a PostDetailsDto.
     *
     * @param post the Post entity to convert
     * @return the converted PostDetailsDto
     */
    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "author.username", target = "authorName")
    PostDetailsDto toPostDetailsDto(Post post);

    /**
     * Converts a PostPayloadDto to a Post entity.
     *
     * @param postPayloadDto the PostPayloadDto to convert
     * @return the converted Post entity
     */
    @Mapping(source = "topicId", target = "topic.id")
    Post toEntity(PostPayloadDto postPayloadDto);
}
