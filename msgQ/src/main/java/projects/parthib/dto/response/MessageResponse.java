package projects.parthib.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private String key;
    private String payload;
    private long timestamp;
    private int partition;
}
