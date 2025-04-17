package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.UserMapper;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Retrieves information about a specific user.
     *
     * @param userId the ID of the user
     * @return a UserDto object
     */
    public UserDto getUserInfos(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userMapper.toDto(foundUser);
    }

    /**
     * Retrieves the user ID based on the provided email or username.
     *
     * @param emailOrUsername the email or username of the user
     * @return the user ID
     * @throws UsernameNotFoundException if the user is not found
     */
    public Long getUserId(String emailOrUsername) {
        User foundUser = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new UsernameNotFoundException(emailOrUsername));

        return foundUser.getId();
    }
}
