package danla.checkboo.common.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes implements ErrorCode {

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "내부 서버 오류"),
	INVALID_PARAMETER(BAD_REQUEST, 1001, "입력값 오류."),
	FAIL_ENCRYPT(HttpStatus.INTERNAL_SERVER_ERROR, 1002, "암호화 실패"),
	FAIL_DECRYPT(HttpStatus.INTERNAL_SERVER_ERROR, 1003, "복호화 실패"),

	ALREADY_SIGNED_UP_EMAIL(BAD_REQUEST, 2001, "이미 가입된 이메일입니다."),
	MEMBER_NOT_FOUND(NOT_FOUND, 2002, "존재하지 않는 회원입니다."),
	PASSWORD_NOT_CORRECT(BAD_REQUEST, 2003, "비밀번호가 일치하지 않습니다."),
	NEED_LOGIN(UNAUTHORIZED, 2004, "로그인이 필요합니다."),
	;

	private final HttpStatus status;
	private final int code;
	private final String message;
}
