package projects.parthib.messageQueue.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@RequiredArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String key;
    private final String payload;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "partition_id")
    private Partition partition;

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
