package danla.checkboo.common.exception;

import danla.checkboo.common.exception.errorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CheckBooException extends RuntimeException {

	private final ErrorCode errorCode;
}
