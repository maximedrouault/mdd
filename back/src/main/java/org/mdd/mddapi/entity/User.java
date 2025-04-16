package org.mdd.mddapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a user entity in the system.
 * A user can subscribe to topics, create posts, and write comments.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * The username of the user. Must be unique and not null.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    /**
     * The email address of the user. Must be unique and not null.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /**
     * The password of the user. Must not be null.
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * The set of topics the user is subscribed to.
     * This relationship is managed by the {@link Topic} entity.
     */
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Topic> subscribedTopics = new LinkedHashSet<>();

    /**
     * The set of posts created by the user.
     * This relationship is managed by the {@link Post} entity.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> createdPosts = new LinkedHashSet<>();

    /**
     * The set of comments created by the user.
     * This relationship is managed by the {@link Comment} entity.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> createdComments = new LinkedHashSet<>();

}
