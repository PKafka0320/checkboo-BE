package danla.checkboo.api.controller.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import danla.checkboo.api.controller.member.dto.SignupRequest;
import danla.checkboo.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService service;

	@PostMapping("/members/signup")
	public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequest request) {
		service.signup(request.email(), request.password(), request.username());
		return ResponseEntity.noContent().build();
	}
}
