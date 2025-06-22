package projects.parthib.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private String payload;
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "partition_id")
    private Partition partition;
}
