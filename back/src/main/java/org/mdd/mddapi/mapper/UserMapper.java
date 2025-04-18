package org.mdd.mddapi.mapper;

import org.mapstruct.*;
import org.mdd.mddapi.dto.auth.request.RegisterPayloadDto;
import org.mdd.mddapi.dto.auth.request.UpdatePayloadDto;
import org.mdd.mddapi.entity.User;
import org.mdd.mddapi.dto.user.response.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Mapper interface for converting between User entities and their corresponding DTOs.
 * Utilizes MapStruct for automatic mapping.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    /**
     * Converts a RegisterPayloadDto to a User entity.
     * Encodes the password using the provided PasswordEncoder.
     *
     * @param registerPayloadDto the RegisterPayloadDto to convert
     * @param passwordEncoder the PasswordEncoder to use for encoding the password
     * @return the converted User entity
     */
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(registerPayloadDto.password()))")
    User toEntity(RegisterPayloadDto registerPayloadDto, @Context PasswordEncoder passwordEncoder);

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to convert
     * @return the converted UserDto
     */
    UserDto toDto(User user);

    /**
     * Partially updates a User entity using an UpdatePayloadDto.
     * Encodes the password using the provided PasswordEncoder.
     *
     * @param updatePayloadDto the UpdatePayloadDto containing the updates
     * @param user the User entity to update
     * @param passwordEncoder the PasswordEncoder to use for encoding the password
     * @return the updated User entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(updatePayloadDto.password()))")
    User partialUpdate(UpdatePayloadDto updatePayloadDto, @MappingTarget User user, @Context PasswordEncoder passwordEncoder);
}
