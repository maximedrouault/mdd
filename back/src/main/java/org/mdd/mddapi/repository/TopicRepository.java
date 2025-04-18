package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repository interface for managing {@link Topic} entities.
 * Provides methods for performing CRUD operations and custom queries.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    /**
     * Retrieves all topics associated with a specific user.
     *
     * @param userId the ID of the user
     * @return a set of topics linked to the user
     */
    Set<Topic> findByUsers_Id(Long userId);
}
