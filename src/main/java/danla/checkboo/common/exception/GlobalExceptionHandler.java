package danla.checkboo.common.exception;

import static danla.checkboo.common.error.ErrorCodes.INTERNAL_SERVER_ERROR;
import static danla.checkboo.common.error.ErrorCodes.INVALID_PARAMETER;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import danla.checkboo.common.error.ErrorResponse;
import danla.checkboo.common.error.FieldError;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		log.warn("MethodArgumentNotValidException 발생: {}", e.getMessage(), e);
		List<FieldError> errors = e.getBindingResult().getFieldErrors().stream()
			.map(error -> FieldError.of(error.getField(), error.getDefaultMessage()))
			.toList();

		return ResponseEntity.badRequest()
			.body(ErrorResponse.of(INVALID_PARAMETER, errors));
	}

	@ExceptionHandler(CheckBooException.class)
	public ResponseEntity<Object> handleCustomException(CheckBooException e) {
		log.warn("CheckBooException 발생: {} - {}", e.getErrorCode().getCode(), e.getErrorCode().getMessage(), e);
		return ResponseEntity.status(e.getErrorCode().getStatus())
			.body(ErrorResponse.of(e.getErrorCode()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception e) {
		log.error("Unhandled Exception 발생: {}", e.getMessage(), e);
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.of(INTERNAL_SERVER_ERROR));
	}
}
