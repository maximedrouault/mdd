package org.mdd.mddapi.dto.post.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}.
 * Represents the details of a subscribed post, including its ID, creation date, title, content, and author name.
 *
 * @param id           The unique identifier of the post.
 * @param creationDate The date and time when the post was created.
 * @param title        The title of the post.
 * @param content      The content of the post.
 * @param authorName   The name of the author of the post.
 */
public record SubscribedPostDto(

        Long id,
        LocalDateTime creationDate,
        String title,
        String content,
        String authorName

) implements Serializable {}