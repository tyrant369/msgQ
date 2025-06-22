package projects.parthib.messageQueue.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "partitions")
@Data
@RequiredArgsConstructor
@Builder
public class Partition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int partitionIndex;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "partition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
