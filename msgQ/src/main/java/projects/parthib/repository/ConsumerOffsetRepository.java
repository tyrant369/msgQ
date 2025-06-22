package projects.parthib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.parthib.model.ConsumerOffset;
import projects.parthib.model.Partition;
import projects.parthib.model.Topic;

import java.util.List;
import java.util.Optional;

public interface ConsumerOffsetRepository extends JpaRepository<ConsumerOffset, Long> {
    Optional<ConsumerOffset> findByConsumerIdAndPartition(String consumerId, Partition partition);
    List<ConsumerOffset> findByConsumerIdAndTopic(String consumerId, Topic topic);
    void deleteByConsumerIdAndTopic(String consumerId, Topic topic);

}
