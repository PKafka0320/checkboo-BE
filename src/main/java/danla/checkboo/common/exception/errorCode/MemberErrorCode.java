package danla.checkboo.common.exception.errorCode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

	ALREADY_SIGNED_UP_EMAIL(HttpStatus.FOUND, "이미 가입된 이메일입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
