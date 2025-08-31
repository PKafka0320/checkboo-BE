package danla.checkboo.domain.member;

import danla.checkboo.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "ltuid_v2", length = 1024)
	private String ltuidV2;

	@Column(name = "ltoken_v2", length = 1024)
	private String ltokenV2;

	public void updateToken(String ltuidV2, String ltokenV2) {
		this.ltuidV2 = ltuidV2;
		this.ltokenV2 = ltokenV2;
	}
}
