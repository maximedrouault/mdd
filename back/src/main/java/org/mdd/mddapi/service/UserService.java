package org.mdd.mddapi.service;

import lombok.RequiredArgsConstructor;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.exception.UserNotFoundException;
import org.mdd.mddapi.mapper.UserMapper;
import org.mdd.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserDto getUserById(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userMapper.toDto(foundUser);
    }
}
