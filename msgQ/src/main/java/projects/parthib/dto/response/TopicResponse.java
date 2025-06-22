package projects.parthib.dto.response;

import lombok.Builder;
import lombok.Data;
import projects.parthib.partition.strategy.PartitionStrategyType;

@Data
@Builder
public class TopicResponse {
    private String name;
    private int partitions;
    private PartitionStrategyType strategy;
}
