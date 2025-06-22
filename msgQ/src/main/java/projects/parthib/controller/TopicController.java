package projects.parthib.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.parthib.dto.request.CreateTopicRequest;
import projects.parthib.dto.response.TopicResponse;
import projects.parthib.partition.strategy.PartitionStrategyType;
import projects.parthib.service.TopicService;

@RestController
@RequestMapping("/api/v1/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid CreateTopicRequest request) {
        TopicResponse response = topicService.createTopic(
                request.getName(),
                request.getPartitions(),
                request.getStrategy()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{topic}")
    public ResponseEntity<String> deleteTopic(
            @PathVariable String topic
    ) {
        topicService.deleteTopic(topic);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("DELETED");
    }
}
