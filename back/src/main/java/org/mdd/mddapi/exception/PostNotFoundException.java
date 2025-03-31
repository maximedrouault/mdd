package org.mdd.mddapi.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("Post with 'ID: " + postId + "' not found.");
    }
}
