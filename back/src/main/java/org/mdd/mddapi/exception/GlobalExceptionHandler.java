package org.mdd.mddapi.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleConstraintViolationException(HandlerMethodValidationException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleIllegalArgumentException(MethodArgumentTypeMismatchException exception) {
        log.warn(exception.getMessage());

        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("An unexpected error occurred: {}", exception.getMessage());

        return ResponseEntity.internalServerError().build();
    }
}