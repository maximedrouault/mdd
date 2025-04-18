package org.mdd.mddapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for managing application-wide exceptions.
 * Provides specific responses for different exception types.
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException.
     *
     * @param exception the exception thrown when a user is not found
     * @return a 404 Not Found response
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    /**
     * Handles TopicNotFoundException.
     *
     * @param exception the exception thrown when a topic is not found
     * @return a 404 Not Found response
     */
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    /**
     * Handles PostNotFoundException.
     *
     * @param exception the exception thrown when a post is not found
     * @return a 404 Not Found response
     */
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    /**
     * Handles HandlerMethodValidationException.
     *
     * @param exception the exception thrown due to validation errors
     * @return a 400 Bad Request response
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleConstraintViolationException(HandlerMethodValidationException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest().build();
    }

    /**
     * Handles MethodArgumentTypeMismatchException.
     *
     * @param exception the exception thrown when a method argument type is invalid
     * @return a 400 Bad Request response
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleIllegalArgumentException(MethodArgumentTypeMismatchException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest().build();
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param exception the exception thrown when a method argument is invalid
     * @return a 400 Bad Request response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest().build();
    }

    /**
     * Handles BadCredentialsException.
     *
     * @param exception the exception thrown when authentication fails
     * @return a 403 Forbidden response
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Handles UserAlreadyExistsException.
     *
     * @param exception the exception thrown when a user already exists
     * @return a 409 Conflict response
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    /**
     * Handles generic exceptions.
     *
     * @param exception the exception thrown for unexpected errors
     * @return a 500 Internal Server Error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("An unexpected error occurred: {}", exception.getMessage());

        return ResponseEntity.internalServerError().build();
    }
}
