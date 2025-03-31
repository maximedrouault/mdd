package org.mdd.mddapi.repository;

import org.mdd.mddapi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Set<Topic> findByUsers_Id(Long userId);
}