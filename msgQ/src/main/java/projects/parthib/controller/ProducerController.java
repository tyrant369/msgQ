package projects.parthib.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.parthib.dto.request.PublishMessageRequest;
import projects.parthib.service.ProducerService;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/{topic}/publish")
    public ResponseEntity<String> publish(
            @PathVariable String topic,
            @RequestBody @Valid PublishMessageRequest request) {

        producerService.publish(
                topic,
                request.getKey(),
                request.getPayload()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("PUBLISHED");
    }
}
