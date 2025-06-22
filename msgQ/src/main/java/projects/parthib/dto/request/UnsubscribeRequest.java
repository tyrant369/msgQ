package projects.parthib.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UnsubscribeRequest {
    @NotBlank(message = "Consumer ID Cannot Be Blank")
    private String consumerId;
}
