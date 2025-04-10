package org.mdd.mddapi.dto.user.response;

import org.mdd.mddapi.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(

        String username,
        String email)

implements Serializable {}