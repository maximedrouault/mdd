package org.mdd.mddapi.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Topic}
 */
public record TopicDto(Long id, String name, String description) implements Serializable {
}