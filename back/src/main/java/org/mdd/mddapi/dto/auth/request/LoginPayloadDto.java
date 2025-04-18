package org.mdd.mddapi.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for handling login payloads.
 * Contains the username or email and the password required for authentication.
 *
 * @param usernameOrEmail The username or email of the user.
 *                        Must not be blank and must have a maximum length of 100 characters.
 * @param password        The password of the user.
 *                        Must not be blank, must have a length between 8 and 100 characters,
 *                        and must include at least:
 *                        - one digit,
 *                        - one lowercase letter,
 *                        - one uppercase letter,
 *                        - one special character.
 */
public record LoginPayloadDto(

        @NotBlank
        @Size(max = 100)
        String usernameOrEmail,

        @NotBlank
        @Size(min = 8, max = 100)
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).*$",
                message = "The password must contain at least 8 characters, including a digit, a lowercase letter," +
                        " an uppercase letter, and a special character.")
        String password

) implements Serializable {}
