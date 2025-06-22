package projects.parthib.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerOffset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consumerId;
    private Long offset;

    @ManyToOne
    @JoinColumn(name = "partition_id")
    private Partition partition;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
