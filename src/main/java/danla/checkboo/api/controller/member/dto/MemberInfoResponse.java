package danla.checkboo.api.controller.member.dto;

import danla.checkboo.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

	private final Long id;
	private final String name;
	private final String email;

	@Builder
	private MemberInfoResponse(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public static MemberInfoResponse from(Member member) {
		return MemberInfoResponse.builder()
			.id(member.getId())
			.name(member.getUsername())
			.email(member.getEmail())
			.build();
	}
}
