package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Provides methods for performing CRUD operations and custom queries.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email or username.
     *
     * @param email    the email of the user
     * @param username the username of the user
     * @return an {@link Optional} containing the user if found, or empty otherwise
     */
    Optional<User> findByEmailOrUsername(String email, String username);

    /**
     * Checks if a user exists with the given email or username.
     *
     * @param email    the email to check
     * @param username the username to check
     * @return true if a user exists with the given email or username, false otherwise
     */
    boolean existsByEmailOrUsername(String email, String username);
}
