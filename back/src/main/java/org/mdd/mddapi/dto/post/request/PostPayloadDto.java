package org.mdd.mddapi.dto.post.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Post}.
 * Represents the payload for creating a new post.
 *
 * @param topicId The ID of the topic associated with the post.
 *                Must not be null and must be a positive value.
 * @param title   The title of the post. Must not be blank.
 * @param content The content of the post. Must not be blank.
 */
public record PostPayloadDto(

        @NotNull @Positive Long topicId,
        @NotBlank String title,
        @NotBlank String content

) implements Serializable {}
