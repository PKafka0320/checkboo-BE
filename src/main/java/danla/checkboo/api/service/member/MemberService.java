package danla.checkboo.api.service.member;

import static danla.checkboo.common.error.ErrorCodes.ALREADY_SIGNED_UP_EMAIL;
import static danla.checkboo.common.error.ErrorCodes.MEMBER_NOT_FOUND;
import static danla.checkboo.common.error.ErrorCodes.PASSWORD_NOT_CORRECT;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import danla.checkboo.api.controller.member.dto.MemberInfoResponse;
import danla.checkboo.api.controller.member.dto.SearchResponse;
import danla.checkboo.common.exception.CheckBooException;
import danla.checkboo.domain.member.Member;
import danla.checkboo.domain.member.MemberRepository;
import danla.checkboo.utility.AesEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final AesEncryptor aesEncryptor;

	public void signup(String email, String password, String username) {
		Optional<Member> searchedEmail = repository.findByEmail(email);
		if (searchedEmail.isPresent()) {
			throw new CheckBooException(ALREADY_SIGNED_UP_EMAIL);
		}

		String encodedPassword = passwordEncoder.encode(password);

		repository.save(Member.builder()
			.email(email)
			.password(encodedPassword)
			.username(username)
			.build()
		);
	}

	@Transactional
	public void updateToken(Long memberId, String uid, String token) {
		Member member = repository.findById(memberId).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));

		String encryptUid = aesEncryptor.encrypt(uid);
		String encryptToken = aesEncryptor.encrypt(token);
		member.updateToken(encryptUid, encryptToken);
	}

	public SearchResponse searchMember(Long memberId) {
		Member member = repository.findById(memberId).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));
		return SearchResponse.from(member);
	}

	public MemberInfoResponse login(String email, String password, HttpServletRequest request) {
		Member member = repository.findByEmail(email).orElseThrow(() -> new CheckBooException(MEMBER_NOT_FOUND));

		if (!passwordEncoder.matches(password, member.getPassword())) {
			throw new CheckBooException(PASSWORD_NOT_CORRECT);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("memberId", member.getId());
		session.setMaxInactiveInterval(30 * 60);

		return MemberInfoResponse.from(member);
	}
}
