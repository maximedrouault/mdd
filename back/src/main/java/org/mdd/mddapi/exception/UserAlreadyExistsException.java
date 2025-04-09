package org.mdd.mddapi.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email, String username) {
        super("User with 'Email: " + email + "' and 'Username: " + username + "' already exists.");
    }
}
