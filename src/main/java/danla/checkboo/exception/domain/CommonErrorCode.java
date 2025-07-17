package danla.checkboo.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "입력값 오류.");

    private final HttpStatus httpStatus;
    private final String message;
}
