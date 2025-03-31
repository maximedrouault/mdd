package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findByTopic_Users_Id(Long userId);
}