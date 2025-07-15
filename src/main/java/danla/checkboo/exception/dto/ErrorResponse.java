package danla.checkboo.exception.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message
) {
}
