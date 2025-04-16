package org.mdd.mddapi.dto.user.response;

import org.mdd.mddapi.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link User}.
 * Represents the details of a user, including their username and email address.
 *
 * @param username The username of the user.
 * @param email    The email address of the user.
 */
public record UserDto(

        String username,
        String email)

implements Serializable {}