package projects.parthib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projects.parthib.model.Message;
import projects.parthib.model.Partition;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByPartitionAndIdGreaterThanOrderByIdAsc(Partition partition, Long offset);

}
