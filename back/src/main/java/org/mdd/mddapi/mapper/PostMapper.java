package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.response.PostDto;
import org.mdd.mddapi.entity.Post;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    @Mapping(source = "author.username", target = "authorName")
    PostDto toDto(Post post);

    Set<PostDto> toDtos(Set<Post> posts);

}