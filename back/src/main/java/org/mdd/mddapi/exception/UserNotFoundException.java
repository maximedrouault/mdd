package org.mdd.mddapi.exception;

/**
 * Exception thrown when a user with the specified ID is not found.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified user ID.
     *
     * @param userId the ID of the user that was not found
     */
    public UserNotFoundException(Long userId) {
        super("User with 'ID: " + userId + "' not found.");
    }
}
