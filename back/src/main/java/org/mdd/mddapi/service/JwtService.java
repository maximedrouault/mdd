package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Service for generating JSON Web Tokens (JWT) for authenticated users.
 */
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.expiration}")
    private long expirationTime;

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    /**
     * Generates a JWT for the given authentication object.
     *
     * @param authentication the authentication object containing user details
     * @return the generated JWT as a string
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiration = now.plus(expirationTime, ChronoUnit.SECONDS);

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("mdd-api")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(authentication.getName())
                .claim("userId", getUserId(authentication.getName()))
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claimsSet);

        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    /**
     * Retrieves the user ID based on the provided email or username.
     *
     * @param emailOrUsername the email or username of the user
     * @return the user ID
     * @throws UsernameNotFoundException if the user is not found
     */
    private Long getUserId(String emailOrUsername) {
        User foundUser = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new UsernameNotFoundException(emailOrUsername));

        return foundUser.getId();
    }
}
