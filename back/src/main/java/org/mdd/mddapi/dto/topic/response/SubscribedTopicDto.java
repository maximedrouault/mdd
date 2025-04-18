package org.mdd.mddapi.dto.topic.response;

import java.io.Serializable;

/**
 * DTO for {@link org.mdd.mddapi.entity.Topic}.
 * Represents the details of a subscribed topic, including its ID, name, and description.
 *
 * @param id          The unique identifier of the topic.
 * @param name        The name of the topic.
 * @param description The description of the topic.
 */
public record SubscribedTopicDto(

        Long id,
        String name,
        String description

) implements Serializable {}