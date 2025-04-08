package org.mdd.mddapi.dto.post.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}
 */
public record PostPayloadDto(

        @NotNull @Positive Long authorId,
        @NotNull @Positive Long topicId,
        @NotBlank String title,
        @NotBlank String content

) implements Serializable {}
