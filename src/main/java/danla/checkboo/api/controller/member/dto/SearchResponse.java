package danla.checkboo.api.controller.member.dto;

import danla.checkboo.domain.member.Member;
import lombok.Builder;

@Builder
public record SearchResponse(
	String email,
	String username,
	String ltuidV2,
	String ltokenV2
) {

	public static SearchResponse from(Member member) {
		return SearchResponse.builder()
			.email(member.getEmail())
			.username(member.getUsername())
			.ltuidV2(member.getLtuidV2())
			.ltokenV2(member.getLtokenV2())
			.build();
	}
}
