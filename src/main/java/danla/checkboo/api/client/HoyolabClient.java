package danla.checkboo.api.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import danla.checkboo.api.client.dto.AttendanceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class HoyolabClient {

	private final String URL = "https://sg-act-nap-api.hoyolab.com/event/luna/zzz/os/sign";
	private final String LANG = "ko-kr";
	private final String ACT_ID = "e202406031448091";
	private final String X_RPC_SIGNGAME = "zzz";

	public void attendance(Long memberId, String uid, String token) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "ltuid_v2=" + uid);
		headers.add("Cookie", "ltoken_v2=" + token);
		headers.add("X-Rpc-Signgame", X_RPC_SIGNGAME);

		AttendanceRequest requestBody = new AttendanceRequest(ACT_ID, LANG);

		HttpEntity<AttendanceRequest> entity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<Object> response = restTemplate.exchange(
			URL,
			HttpMethod.POST,
			entity,
			Object.class
		);
		Object body = response.getBody();
		log.info("attendance check of member {} result: {}", memberId, body);
	}
}
