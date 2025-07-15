package danla.checkboo.exception.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    ALREADY_SIGNED_UP_EMAIL(HttpStatus.FOUND, "이미 가입된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
