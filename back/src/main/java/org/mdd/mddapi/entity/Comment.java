package org.mdd.mddapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

/**
 * Represents a comment entity in the system.
 * A comment is associated with a user (author) and a post.
 */
@Getter
@Setter
@Entity
@Table(name = "comments")
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Unique identifier for the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The date and time when the comment was created.
     * This field is automatically populated and cannot be updated.
     */
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * The content of the comment.
     */
    @Column(nullable = false)
    private String content;

    /**
     * The user who authored the comment.
     * This is a mandatory relationship.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * The post to which the comment belongs.
     * This is a mandatory relationship.
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

}
