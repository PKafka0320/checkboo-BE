package danla.checkboo.common.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import danla.checkboo.common.exception.CheckBooException;
import danla.checkboo.common.exception.dto.ErrorResponse;
import danla.checkboo.common.exception.errorCode.CommonErrorCode;
import danla.checkboo.common.exception.errorCode.ErrorCode;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CheckBooException.class)
	public ResponseEntity<Object> handleCustomException(CheckBooException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		return handleException(errorCode);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex) {
		log.warn("handleAllException", ex);
		ErrorCode errorCode = CommonErrorCode.SERVER_ERROR;
		return handleException(errorCode);
	}

	@Nonnull
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		@Nonnull MethodArgumentNotValidException ex,
		@Nonnull HttpHeaders headers,
		@Nonnull HttpStatusCode status,
		@Nonnull WebRequest request) {
		log.warn("handleIllegalArgument", ex);
		ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
		return handleException(ex, errorCode);
	}

	private ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(makeErrorResponse(ex, errorCode));
	}

	private ErrorResponse makeErrorResponse(MethodArgumentNotValidException ex, ErrorCode errorCode) {
		List<ErrorResponse.ValidationError> validationErrorList = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(ErrorResponse.ValidationError::of)
			.collect(Collectors.toList());

		return ErrorResponse.builder()
			.code(errorCode.name())
			.message(errorCode.getMessage())
			.errors(validationErrorList)
			.build();
	}

	private ResponseEntity<Object> handleException(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getHttpStatus())
			.body(makeErrorResponse(errorCode));
	}

	private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
		return ErrorResponse.builder()
			.code(errorCode.name())
			.message(errorCode.getMessage())
			.build();
	}
}
