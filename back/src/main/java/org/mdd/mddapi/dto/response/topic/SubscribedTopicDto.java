package org.mdd.mddapi.dto.response.topic;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Topic}
 */
public record SubscribedTopicDto(

        Long id,
        String name,
        String description

) implements Serializable {}