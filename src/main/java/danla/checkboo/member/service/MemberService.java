package danla.checkboo.member.service;

import danla.checkboo.exception.domain.RestApiException;
import danla.checkboo.exception.domain.MemberErrorCode;
import danla.checkboo.member.domain.Member;
import danla.checkboo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    @Transactional
    public void signup(String email, String password, String username) {
        Optional<Member> searchedEmail = repository.findByEmail(email);
        if (searchedEmail.isPresent()) {
            throw new RestApiException(MemberErrorCode.ALREADY_SIGNED_UP_EMAIL);
        }

        repository.save(Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .build()
        );
    }
}
