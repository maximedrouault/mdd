package org.mdd.mddapi.exception;

/**
 * Exception thrown when a topic with the specified ID is not found.
 */
public class TopicNotFoundException extends RuntimeException {

    /**
     * Constructs a new TopicNotFoundException with the specified topic ID.
     *
     * @param topicId the ID of the topic that was not found
     */
    public TopicNotFoundException(Long topicId) {
        super("Topic with 'ID: " + topicId + "' not found.");
    }
}
