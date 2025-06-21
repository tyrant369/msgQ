package projects.parthib.messageQueue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messages")
@Data
public class Message {

    private final String key;
    private final String payload;
    private final long timestamp;
}
