package danla.checkboo.exception.handler;

import danla.checkboo.exception.domain.CommonErrorCode;
import danla.checkboo.exception.domain.ErrorCode;
import danla.checkboo.exception.domain.RestApiException;
import danla.checkboo.exception.dto.ErrorResponse;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return handleException(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
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
