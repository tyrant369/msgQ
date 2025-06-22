package projects.parthib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.parthib.model.Partition;
import projects.parthib.model.Topic;

import java.util.List;
import java.util.Optional;

public interface PartitionRepository extends JpaRepository<Partition, Long> {
    List<Partition> findByTopic(Topic topic);
    Optional<Partition> findByTopicAndIndex(Topic topic, int index);
}
