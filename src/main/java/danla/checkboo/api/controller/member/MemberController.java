package danla.checkboo.api.controller.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import danla.checkboo.api.controller.member.dto.LoginRequest;
import danla.checkboo.api.controller.member.dto.SearchResponse;
import danla.checkboo.api.controller.member.dto.SignupRequest;
import danla.checkboo.api.controller.member.dto.UpdateTokenRequest;
import danla.checkboo.api.service.member.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService service;

	@GetMapping("/{memberId}")
	public ResponseEntity<SearchResponse> search(@PathVariable Long memberId) {
		return ResponseEntity.ok(service.searchMember(memberId));
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

	@PutMapping("/{memberId}/token")
	public ResponseEntity<Void> updateToken(
		@PathVariable Long memberId,
		@RequestBody @Valid UpdateTokenRequest request
	) {
		service.updateToken(memberId, request.ltuidV2(), request.ltokenV2());
		return ResponseEntity.noContent().build();
	}
}
