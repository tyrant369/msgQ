package projects.parthib.dto.error;

import lombok.Builder;
import lombok.Data;
import projects.parthib.exception.ErrorCode;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private ErrorCode code;
    private String message;
    private LocalDateTime timestamp;
}
