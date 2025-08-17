package danla.checkboo.common.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

	String name();

	HttpStatus getHttpStatus();

	String getMessage();
}
