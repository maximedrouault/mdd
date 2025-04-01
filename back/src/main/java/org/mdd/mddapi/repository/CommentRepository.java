package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Set<Comment> findByPost_Id(Long postId);
}