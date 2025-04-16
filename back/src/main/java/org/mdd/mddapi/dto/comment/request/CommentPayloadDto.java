package org.mdd.mddapi.dto.comment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Comment}
 */
public record CommentPayloadDto(

        @NotBlank String content,
        @NotNull @Positive Long postId

) implements Serializable {}