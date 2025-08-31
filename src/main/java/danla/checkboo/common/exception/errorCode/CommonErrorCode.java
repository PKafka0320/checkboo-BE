package danla.checkboo.common.exception.errorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

	SERVER_ERROR(INTERNAL_SERVER_ERROR, "내부 서버 오류"),
	INVALID_PARAMETER(BAD_REQUEST, "입력값 오류."),
	FAIL_ENCRYPT(INTERNAL_SERVER_ERROR, "암호화 실패"),
	FAIL_DECRYPT(INTERNAL_SERVER_ERROR, "복호화 실패"),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
