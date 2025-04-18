package org.mdd.mddapi.dto.post.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}.
 * Represents the details of a post, including its ID, title, creation date, author name, topic name, and content.
 *
 * @param id           The unique identifier of the post.
 * @param title        The title of the post.
 * @param creationDate The date and time when the post was created.
 * @param authorName   The name of the author of the post.
 * @param topicName    The name of the topic associated with the post.
 * @param content      The content of the post.
 */
public record PostDetailsDto(

        Long id,
        String title,
        LocalDateTime creationDate,
        String authorName,
        String topicName,
        String content

) implements Serializable {}