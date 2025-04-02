package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.post.PostDetailsDto;
import org.mdd.mddapi.dto.response.post.SubscribedPostDto;
import org.mdd.mddapi.entity.Post;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    // Handle the conversion between PostDto and Post entity
    @Mapping(source = "author.username", target = "authorName")
    SubscribedPostDto toSubscribedPostDto(Post post);

    Set<SubscribedPostDto> toSubscribedPostDto(Set<Post> posts);


    // Handle the conversion between PostDetailsDto and Post entity
    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "author.username", target = "authorName")
    PostDetailsDto toPostDetailsDto(Post post);
}