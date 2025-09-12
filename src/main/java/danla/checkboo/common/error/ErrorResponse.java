package danla.checkboo.common.error;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
public record ErrorResponse(
	HttpStatus status,
	int code,
	String message,
	@JsonInclude(JsonInclude.Include.NON_EMPTY) List<FieldError> errors
) {
	public static ErrorResponse of(ErrorCode errorCode) {
		return ErrorResponse.builder()
			.status(errorCode.getStatus())
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.errors(List.of())
			.build();
	}

	public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
		return ErrorResponse.builder()
			.status(errorCode.getStatus())
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.errors(errors)
			.build();
	}
}
