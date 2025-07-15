package danla.checkboo.member.controller;

import danla.checkboo.member.dto.SignupRequest;
import danla.checkboo.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/members/signup")
    public ResponseEntity<Object> signup(@Valid SignupRequest request) {
        service.signup(request.getEmail(), request.getPassword(), request.getUsername());
        return ResponseEntity.noContent().build();
    }
}
