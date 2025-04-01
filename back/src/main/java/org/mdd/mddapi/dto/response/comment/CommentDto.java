package org.mdd.mddapi.dto.response.comment;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Comment}
 */
public record CommentDto(

        Long id,
        String authorName,
        String content

) implements Serializable {}