package projects.parthib.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import projects.parthib.partition.strategy.PartitionStrategyType;

@Data
public class CreateTopicRequest {
    @NotBlank(message = "Topic Name Cannot Be Blank")
    private String name;

    @Min(value = 1, message = "Partition Count Must Be > 0")
    private int partitions;

    private PartitionStrategyType strategy = PartitionStrategyType.KEY_HASH;
}
