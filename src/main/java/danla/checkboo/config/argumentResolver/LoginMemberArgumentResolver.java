package danla.checkboo.config.argumentResolver;

import static danla.checkboo.common.error.ErrorCodes.NEED_LOGIN;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import danla.checkboo.annotation.LoginMember;
import danla.checkboo.common.exception.CheckBooException;
import danla.checkboo.domain.member.Member;
import danla.checkboo.domain.member.MemberRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	private final MemberRepository memberRepository;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginMember.class)
			&& Member.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	@Nonnull
	public Object resolveArgument(
		@Nonnull MethodParameter parameter,
		@Nonnull ModelAndViewContainer mavContainer,
		@Nonnull NativeWebRequest webRequest,
		@Nonnull WebDataBinderFactory binderFactory
	) throws Exception {
		HttpServletRequest request = ((ServletWebRequest)webRequest).getRequest();
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("memberId") == null) {
			throw new CheckBooException(NEED_LOGIN);
		}

		Long memberId = (Long)session.getAttribute("memberId");
		return memberRepository.findById(memberId).orElseThrow(() -> new CheckBooException(NEED_LOGIN));
	}
}
