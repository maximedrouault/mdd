package org.mdd.mddapi.dto.response.post;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}
 */
public record PostDetailsDto(

        Long id,
        String title,
        LocalDateTime creationDate,
        String authorName,
        String topicName,
        String content

) implements Serializable {}