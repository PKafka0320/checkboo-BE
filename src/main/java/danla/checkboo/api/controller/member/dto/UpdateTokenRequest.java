package danla.checkboo.api.controller.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTokenRequest(

	@NotBlank(message = "ltuid_v2 값은 필수 입력 값입니다.")
	@Size(max = 500, message = "ltuid_v2 값은 최대 500자입니다.")
	String ltuidV2,

	@NotBlank(message = "ltoken_v2 값은 필수 입력 값입니다.")
	@Size(max = 500, message = "ltoken_v2 값은 최대 500자입니다.")
	String ltokenV2
) {
}
