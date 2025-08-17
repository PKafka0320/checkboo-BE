package danla.checkboo.api.service.member;

import static danla.checkboo.common.exception.errorCode.MemberErrorCode.ALREADY_SIGNED_UP_EMAIL;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import danla.checkboo.common.exception.CheckBooException;
import danla.checkboo.domain.member.Member;
import danla.checkboo.domain.member.MemberRepository;
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
}
