package projects.parthib.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublishMessageRequest {
    @NotBlank(message = "Key Cannot Be blank")
    private String key;

    @NotBlank(message = "Payload Cannot Be Blank")
    private String payload;
}
