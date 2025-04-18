package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.auth.request.LoginPayloadDto;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.dto.auth.request.UpdatePayloadDto;
import org.mdd.mddapi.dto.auth.response.AuthTokenDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.UserAlreadyExistsException;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.UserMapper;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

/**
 * Service class for handling authentication and user-related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Authenticates a user and generates an authentication token.
     *
     * @param loginPayloadDto the login payload containing username/email and password
     * @return an {@link AuthTokenDto} containing the generated authentication token
     */
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

    /**
     * Registers a new user in the system.
     *
     * @param registerPayloadDto the registration payload containing user details
     * @throws UserAlreadyExistsException if a user with the same email or username already exists
     */
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

    /**
     * Updates an existing user's information.
     *
     * @param updatePayloadDto the update payload containing updated user details
     * @param userId the ID of the user to update
     * @throws UserNotFoundException if the user with the given ID is not found
     */
    public void updateUser(UpdatePayloadDto updatePayloadDto, Long userId) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

        User updatedUser = userMapper.partialUpdate(updatePayloadDto, foundUser, passwordEncoder);

        userRepository.save(updatedUser);
    }

    /**
     * Extracts the user ID from a given JWT token.
     *
     * @param authToken the JWT token
     * @return the user ID extracted from the token
     */
    public Long getUserIdFromToken(Jwt authToken) {
        return authToken.getClaim("userId");
    }
}
