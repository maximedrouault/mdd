package org.mdd.mddapi.exception;

/**
 * Exception thrown when a user with the specified email and username already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExistsException with the specified email and username.
     *
     * @param email    the email of the user that already exists
     * @param username the username of the user that already exists
     */
    public UserAlreadyExistsException(String email, String username) {
        super("User with 'Email: " + email + "' and 'Username: " + username + "' already exists.");
    }
}
