package danla.checkboo.common.exception.dto;

import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
public record ErrorResponse(
	String code,
	String message,
	@JsonInclude(JsonInclude.Include.NON_EMPTY) List<ValidationError> errors
) {

	@Builder
	public record ValidationError(String field, String message) {

		public static ValidationError of(final FieldError fieldError) {
			return ValidationError.builder()
				.field(fieldError.getField())
				.message(fieldError.getDefaultMessage())
				.build();
		}
	}
}
