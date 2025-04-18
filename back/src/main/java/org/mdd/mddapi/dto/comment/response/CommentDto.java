package org.mdd.mddapi.dto.comment.response;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Comment}.
 * Represents the data of a comment, including its ID, author name, and content.
 *
 * @param id         The unique identifier of the comment.
 * @param authorName The name of the author of the comment.
 * @param content    The content of the comment.
 */
public record CommentDto(

        Long id,
        String authorName,
        String content

) implements Serializable {}