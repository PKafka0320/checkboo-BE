package danla.checkboo.exception.dto;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record ErrorResponse(
        String code,
        String message
) {
}
