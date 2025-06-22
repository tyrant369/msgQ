package projects.parthib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projects.parthib.dto.response.MessageResponse;
import projects.parthib.exception.TopicNotFoundException;
import projects.parthib.model.*;
import projects.parthib.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final TopicRepository topicRepository;
    private final MessageRepository messageRepository;
    private final ConsumerOffsetRepository offsetRepository;

    public void subscribe(String topicName, String consumerId) {
        Topic topic = topicRepository.findByTopic(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        for (Partition partition : topic.getPartitions()) {
            ConsumerOffset offset = ConsumerOffset.builder()
                    .consumerId(consumerId)
                    .partition(partition)
                    .topic(topic)
                    .offset(0L)
                    .build();

            offsetRepository.save(offset);
        }
    }

    public void unsubscribe(String topicName, String consumerId) {
        Topic topic = topicRepository.findByTopic(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        List<ConsumerOffset> offsets = offsetRepository.findByConsumerIdAndTopic(consumerId, topic);
        offsetRepository.deleteAll(offsets);
    }

    public List<MessageResponse> consume(String topicName, String consumerId) {
        Topic topic = topicRepository.findByTopic(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        List<ConsumerOffset> offsets = offsetRepository.findByConsumerIdAndTopic(consumerId, topic);
        List<MessageResponse> responseList = new ArrayList<>();

        for (ConsumerOffset offset : offsets) {
            Long lastSeenId = offset.getOffset() != null ? offset.getOffset() : 0L;

            List<Message> messages = messageRepository
                    .findByPartitionAndIdGreaterThanOrderByIdAsc(offset.getPartition(), lastSeenId);

            if (!messages.isEmpty()) {
                for (Message message : messages) {
                    responseList.add(MessageResponse.builder()
                            .key(message.getKey())
                            .payload(message.getPayload())
                            .timestamp(message.getTimestamp())
                            .partition(offset.getPartition().getIndex())
                            .build());
                }

                Message last = messages.get(messages.size() - 1);
                offset.setOffset(last.getId());
                offsetRepository.save(offset);
            }
        }

        return responseList;
    }
}
