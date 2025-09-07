package danla.checkboo.api.controller.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignupRequest(

	@Pattern(
		regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
		message = "이메일 형식이 올바르지 않습니다."
	)
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	String email,

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	String password,

	@NotBlank(message = "사용자명은 필수 입력 값입니다.")
	String username
) {
}
