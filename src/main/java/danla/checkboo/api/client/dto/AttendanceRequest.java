package danla.checkboo.api.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AttendanceRequest(
	@JsonProperty("act_id") String actId,
	String lang
) {
}
