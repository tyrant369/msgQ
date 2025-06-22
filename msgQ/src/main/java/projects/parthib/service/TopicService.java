package projects.parthib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projects.parthib.dto.response.TopicResponse;
import projects.parthib.exception.TopicNotFoundException;
import projects.parthib.model.Partition;
import projects.parthib.model.Topic;
import projects.parthib.partition.strategy.PartitionStrategyType;
import projects.parthib.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicResponse createTopic(String name, int partitionCount, PartitionStrategyType strategy) {
        List<Partition> partitions = new ArrayList<>();
        for (int i = 0; i < partitionCount; i++) {
            partitions.add(Partition.builder().index(i).build());
        }

        Topic topic = Topic.builder()
                .topic(name)
                .partitionCount(partitionCount)
                .strategy(strategy)
                .partitions(partitions)
                .build();

        partitions.forEach(p -> p.setTopic(topic));
        topicRepository.save(topic);

        return TopicResponse.builder()
                .name(topic.getTopic())
                .partitions(topic.getPartitionCount())
                .strategy(topic.getStrategy())
                .build();
    }

    public void deleteTopic(String topicName) {
        Topic topic = topicRepository.findByTopic(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));
        topicRepository.delete(topic);
    }
}
