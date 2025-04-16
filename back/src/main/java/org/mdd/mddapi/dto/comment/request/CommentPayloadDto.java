package org.mdd.mddapi.dto.comment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Comment}.
 * Represents the payload for creating a new comment.
 *
 * @param content The content of the comment. Must not be blank.
 * @param postId  The ID of the post associated with the comment. Must not be null and must be a positive value.
 */
public record CommentPayloadDto(

        @NotBlank String content,
        @NotNull @Positive Long postId

) implements Serializable {}