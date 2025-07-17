package danla.checkboo.member.domain;

import danla.checkboo.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "ltuid_v2")
    private String ltuidV2;

    @Column(name = "ltoken_v2")
    private String ltokenV2;

    public void updateToken(String ltuidV2, String ltokenV2) {
        this.ltuidV2 = ltuidV2;
        this.ltokenV2 = ltokenV2;
    }
}
