package projects.parthib.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.parthib.dto.request.ConsumeRequest;
import projects.parthib.dto.request.SubscribeRequest;
import projects.parthib.dto.request.UnsubscribeRequest;
import projects.parthib.dto.response.MessageResponse;
import projects.parthib.model.Message;
import projects.parthib.service.ConsumerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping("/{topic}/subscribe")
    public ResponseEntity<String> subscribe(
            @PathVariable String topic,
            @RequestBody @Valid SubscribeRequest request) {
        consumerService.subscribe(topic, request.getConsumerId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("SUBSCRIBED");
    }

    @DeleteMapping("/{topic}/unsubscribe")
    public ResponseEntity<String> unsubscribe(
            @PathVariable String topic,
            @RequestBody @Valid UnsubscribeRequest request) {
        consumerService.unsubscribe(topic, request.getConsumerId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("UNSUBSCRIBED");
    }


    @PostMapping("/{topic}/consume")
    public ResponseEntity<List<MessageResponse>> consume(
            @PathVariable String topic,
            @RequestBody @Valid ConsumeRequest request) {
        List<MessageResponse> response = consumerService.consume(topic, request.getConsumerId());
        if (response == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
