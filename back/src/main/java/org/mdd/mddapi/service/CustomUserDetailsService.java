package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) {
        User user = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(usernameOrEmail));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
