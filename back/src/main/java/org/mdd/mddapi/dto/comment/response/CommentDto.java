package org.mdd.mddapi.dto.comment.response;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Comment}
 */
public record CommentDto(

        Long id,
        String authorName,
        String content

) implements Serializable {}