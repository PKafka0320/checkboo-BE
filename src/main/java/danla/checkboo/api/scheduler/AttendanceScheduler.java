package danla.checkboo.api.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import danla.checkboo.api.client.HoyolabClient;
import danla.checkboo.domain.member.Member;
import danla.checkboo.domain.member.MemberRepository;
import danla.checkboo.utility.AesEncryptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AttendanceScheduler {

	private final MemberRepository memberRepository;
	private final AesEncryptor aesEncryptor;
	private final HoyolabClient hoyolabClient;

	@Scheduled(cron = "0 30 1 * * *")
	public void callExternalApiDaily() {
		log.info("schedule task start");
		List<Member> members = memberRepository.findAll();
		for (Member member : members) {
			try {
				String uid = aesEncryptor.decrypt(member.getLtuidV2());
				String token = aesEncryptor.decrypt(member.getLtokenV2());
				hoyolabClient.attendance(member.getId(), uid, token);
			} catch (Exception e) {
				log.error("출석 API 호출 실패: memberId={}", member.getId(), e);
			}
		}
	}
}
