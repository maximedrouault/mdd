package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.auth.request.LoginPayloadDto;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.dto.auth.response.AuthTokenDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.UserAlreadyExistsException;
import org.mdd.mddapi.mapper.UserMapper;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthTokenDto getAuthToken(LoginPayloadDto loginPayloadDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPayloadDto.usernameOrEmail(),
                        loginPayloadDto.password()
                )
        );

        String token = jwtService.generateToken(authentication);

        return new AuthTokenDto(token);
    }

    public void registerUser(RegisterPayloadDto registerPayloadDto) {
        String username = registerPayloadDto.username();
        String userEmail = registerPayloadDto.email();

        boolean isRegistered = userRepository.existsByEmailOrUsername(userEmail, username);

        if (isRegistered) {
            throw new UserAlreadyExistsException(userEmail, username);
        }

        User userToAdd = userMapper.toEntity(registerPayloadDto, passwordEncoder);
        userRepository.save(userToAdd);
    }
}
