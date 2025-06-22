package projects.parthib.model;

import jakarta.persistence.*;
import lombok.*;
import projects.parthib.partition.strategy.PartitionStrategyType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private int partitionCount;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partition> partitions = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PartitionStrategyType strategy;
}
