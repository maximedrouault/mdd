package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repository interface for managing {@link Comment} entities.
 * Provides methods for performing CRUD operations and custom queries.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieves all comments associated with a specific post.
     *
     * @param postId the ID of the post
     * @return a set of comments linked to the given post
     */
    Set<Comment> findByPost_Id(Long postId);
}
