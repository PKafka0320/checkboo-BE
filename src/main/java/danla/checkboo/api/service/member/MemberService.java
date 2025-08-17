package danla.checkboo.api.service.member;

import static danla.checkboo.common.exception.errorCode.MemberErrorCode.ALREADY_SIGNED_UP_EMAIL;
import static danla.checkboo.common.exception.errorCode.MemberErrorCode.MEMBER_NOT_FOUND;
import static danla.checkboo.common.exception.errorCode.MemberErrorCode.PASSWORD_NOT_CORRECT;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import danla.checkboo.api.controller.member.dto.SearchResponse;
import danla.checkboo.common.exception.CheckBooException;
import danla.checkboo.domain.member.Member;
import danla.checkboo.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository repository;

	@Transactional
	public void signup(String email, String password, String username) {
		Optional<Member> searchedEmail = repository.findByEmail(email);
		if (searchedEmail.isPresent()) {
			throw new CheckBooException(ALREADY_SIGNED_UP_EMAIL);
		}

		repository.save(Member.builder()
			.email(email)
			.password(password)
			.username(username)
			.build()
		);
	}

	@Transactional
	public void updateToken(Long memberId, String uid, String token) {
		Member member = repository.findById(memberId).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));
		member.updateToken(uid, token);
	}

	public SearchResponse searchMember(Long memberId) {
		Member member = repository.findById(memberId).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));
		return SearchResponse.from(member);
	}

	public void login(String username, String password, HttpServletRequest request) {
		Member member = repository.findByUsername(username).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));

		if (!member.getPassword().equals(password)) {
			throw new CheckBooException(PASSWORD_NOT_CORRECT);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("memberId", member.getId());
		session.setMaxInactiveInterval(30 * 60);
	}
}
