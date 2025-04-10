package org.mdd.mddapi.dto.auth.request;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.User}
 */
public record UpdatePayloadDto(

        @Positive
        Long userId,

        @NotBlank
        @Size(max = 100)
        String username,

        @NotBlank
        @Email
        @Size(max = 100)
        String email,

        @NotBlank
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).*$",
                message = "The password must contain at least 8 characters, including a digit, a lowercase letter," +
                        " an uppercase letter, and a special character.")
        String password

) implements Serializable {}
