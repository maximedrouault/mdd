package org.mdd.mddapi.dto.auth.response;

import java.io.Serializable;

/**
 * DTO for representing an authentication token response.
 * Contains the token required for authenticated requests.
 *
 * @param token The authentication token.
 */
public record AuthTokenDto(

        String token

) implements Serializable {}
