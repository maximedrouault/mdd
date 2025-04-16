package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repository interface for managing {@link Post} entities.
 * Provides methods for performing CRUD operations and custom queries.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Retrieves all posts associated with a specific user's topics.
     *
     * @param userId the ID of the user
     * @return a set of posts linked to the user's topics
     */
    Set<Post> findByTopic_Users_Id(Long userId);
}
