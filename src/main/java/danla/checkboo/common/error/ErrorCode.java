package danla.checkboo.common.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	HttpStatus getStatus();

	int getCode();

	String getMessage();
}
