package projects.parthib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.parthib.model.Topic;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTopic(String name);
}
