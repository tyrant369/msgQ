package projects.parthib.partition.strategy;

import org.springframework.stereotype.Component;
import projects.parthib.model.Topic;

@Component
public class KeyHashPartitionStrategy implements PartitionStrategy {

    @Override
    public int selectPartition(Topic topic, String key) {
        if (key == null || key.isEmpty()) return 0;
        return Math.abs(key.hashCode()) % topic.getPartitionCount();
    }

    @Override
    public PartitionStrategyType getType() {
        return PartitionStrategyType.KEY_HASH;
    }
}
