package projects.parthib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projects.parthib.exception.TopicNotFoundException;
import projects.parthib.model.Message;
import projects.parthib.model.Partition;
import projects.parthib.model.Topic;
import projects.parthib.partition.factory.PartitionStrategyFactory;
import projects.parthib.partition.strategy.PartitionStrategy;
import projects.parthib.repository.MessageRepository;
import projects.parthib.repository.TopicRepository;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final TopicRepository topicRepository;
    private final MessageRepository messageRepository;
    private final PartitionStrategyFactory strategyFactory;

    public void publish(String topicName, String key, String payload) {
        Topic topic = topicRepository.findByTopic(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        PartitionStrategy strategy = strategyFactory.getStrategy(topic.getStrategy());
        int index = strategy.selectPartition(topic, key);
        Partition partition = topic.getPartitions().get(index);

        Message message = Message.builder()
                .key(key)
                .payload(payload)
                .timestamp(System.currentTimeMillis())
                .partition(partition)
                .build();

        messageRepository.save(message);
    }
}
