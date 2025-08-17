package danla.checkboo.api.controller.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import danla.checkboo.annotation.LoginMember;
import danla.checkboo.api.controller.member.dto.LoginRequest;
import danla.checkboo.api.controller.member.dto.SearchResponse;
import danla.checkboo.api.controller.member.dto.SignupRequest;
import danla.checkboo.api.controller.member.dto.UpdateTokenRequest;
import danla.checkboo.api.service.member.MemberService;
import danla.checkboo.domain.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService service;

	@GetMapping("/my")
	public ResponseEntity<SearchResponse> search(@LoginMember Member member) {
		return ResponseEntity.ok(service.searchMember(member.getId()));
	}

	@PutMapping("/my/token")
	public ResponseEntity<Void> updateToken(
		@LoginMember Member member,
		@RequestBody @Valid UpdateTokenRequest request
	) {
		service.updateToken(member.getId(), request.ltuidV2(), request.ltokenV2());
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest request) {
		service.signup(request.email(), request.password(), request.username());
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpServletRequest servlet) {
		service.login(request.username(), request.password(), servlet);
		return ResponseEntity.noContent().build();
	}
}
