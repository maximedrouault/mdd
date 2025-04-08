package org.mdd.mddapi.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

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
