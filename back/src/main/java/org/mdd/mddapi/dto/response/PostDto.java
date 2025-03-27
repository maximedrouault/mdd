package org.mdd.mddapi.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}
 */
public record PostDto(

        Long id,
        LocalDateTime creationDate,
        String title,
        String content,
        String authorName

) implements Serializable {}