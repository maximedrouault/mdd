package org.mdd.mddapi.dto.auth.response;

import java.io.Serializable;

public record AuthTokenDto(

        String token

) implements Serializable {}
