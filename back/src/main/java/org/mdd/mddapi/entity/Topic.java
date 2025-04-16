package org.mdd.mddapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a topic entity in the system.
 * A topic can have a name, description, associated users, and posts.
 */
@Getter
@Setter
@Entity
@Table(name = "topics")
@DynamicInsert
@DynamicUpdate
public class Topic {

    /**
     * The unique identifier for the topic.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the topic. Must be unique and cannot exceed 100 characters.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /**
     * A brief description of the topic.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The set of users associated with this topic.
     * This relationship is many-to-many.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_topics",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

    /**
     * The set of posts associated with this topic.
     * This relationship is one-to-many.
     */
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Post> posts = new LinkedHashSet<>();

}
