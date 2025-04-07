package org.mdd.mddapi.service;

import org.mdd.mddapi.dto.auth.request.LoginDto;
import org.mdd.mddapi.dto.auth.response.AuthTokenDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    public AuthTokenDto getAuthToken(LoginDto loginDto) {
        return new AuthTokenDto("fakeToken");
    }
}
