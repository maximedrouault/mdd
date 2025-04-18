package org.mdd.mddapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a post entity in the application.
 * A post is created by an author, belongs to a topic, and can have multiple comments.
 */
@Getter
@Setter
@Entity
@Table(name = "posts")
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    /**
     * The unique identifier of the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The creation date of the post. This field is automatically set when the post is created.
     */
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * The title of the post. It must not exceed 100 characters.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * The content of the post. Stored as a large object (TEXT).
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * The author of the post. This is a mandatory field.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * The topic to which the post belongs. This is a mandatory field.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    /**
     * The set of comments associated with the post. Comments are removed if the post is deleted.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

}
