package projects.parthib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.parthib.model.ConsumerOffset;
import projects.parthib.model.Partition;

import java.util.Optional;

public interface ConsumerOffsetRepository extends JpaRepository<ConsumerOffset, Long> {
    Optional<ConsumerOffset> findByConsumerIdAndPartition(String consumerId, Partition partition);
}
