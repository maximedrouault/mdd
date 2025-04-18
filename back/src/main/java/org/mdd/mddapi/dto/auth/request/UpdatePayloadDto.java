package org.mdd.mddapi.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.User}.
 * Contains the necessary information for updating a user's details.
 *
 * @param username The username. Must not be blank and must have a maximum length of 100 characters.
 * @param email    The user's email address. Must be valid, not blank, and have a maximum length of 100 characters.
 * @param password The user's password. Must not be blank, must have a length between 8 and 100 characters,
 *                 and must include at least:
 *                 - one digit,
 *                 - one lowercase letter,
 *                 - one uppercase letter,
 *                 - one special character.
 */
public record UpdatePayloadDto(

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
