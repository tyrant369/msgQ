package projects.parthib.partition.factory;

import org.springframework.stereotype.Component;
import projects.parthib.partition.strategy.PartitionStrategy;
import projects.parthib.partition.strategy.PartitionStrategyType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PartitionStrategyFactory {

    private final Map<PartitionStrategyType, PartitionStrategy> strategyMap = new EnumMap<>(PartitionStrategyType.class);

    public PartitionStrategyFactory(List<PartitionStrategy> strategies) {
        for (PartitionStrategy strategy : strategies) {
            strategyMap.put(strategy.getType(), strategy);
        }
    }

    public PartitionStrategy getStrategy(PartitionStrategyType type) {
        return strategyMap.getOrDefault(type, strategyMap.get(PartitionStrategyType.KEY_HASH));
    }
}
