package danla.checkboo.common.exception;

import danla.checkboo.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class CheckBooException extends RuntimeException {

	private final ErrorCode errorCode;

	public CheckBooException(final ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
