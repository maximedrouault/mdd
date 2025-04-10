package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    // Handles the conversion from RegisterPayloadDto to User entity
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(registerPayloadDto.password()))")
    User toEntity(RegisterPayloadDto registerPayloadDto, @Context PasswordEncoder passwordEncoder);

    // Handles the conversion from User entity to UserDto
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}