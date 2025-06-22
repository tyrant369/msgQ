package projects.parthib.partition.strategy;

import projects.parthib.model.Topic;

public interface PartitionStrategy {
    int selectPartition(Topic topic, String key);
    PartitionStrategyType getType();
}
