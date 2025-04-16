package org.mdd.mddapi.exception;

/**
 * Exception thrown when a post with the specified ID is not found.
 */
public class PostNotFoundException extends RuntimeException {

    /**
     * Constructs a new PostNotFoundException with the specified post ID.
     *
     * @param postId the ID of the post that was not found
     */
    public PostNotFoundException(Long postId) {
        super("Post with 'ID: " + postId + "' not found.");
    }
}
