package danla.checkboo.api.controller.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Size(max = 200, message = "이메일은 최대 200자입니다.")
	String email,

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Size(max = 20, message = "비밀번호는 최대 20자입니다.")
	String password,

	@NotBlank(message = "사용자명은 필수 입력 값입니다.")
	@Size(max = 20, message = "사용자명은 최대 20자입니다.")
	String username
) {
}
