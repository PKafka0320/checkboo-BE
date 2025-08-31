package danla.checkboo.common.exception.errorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

	ALREADY_SIGNED_UP_EMAIL(FOUND, "이미 가입된 이메일입니다."),
	MEMBER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다."),
	PASSWORD_NOT_CORRECT(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
	NEED_LOGIN(BAD_REQUEST, "로그인이 필요합니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
