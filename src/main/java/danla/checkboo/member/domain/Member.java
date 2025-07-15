package danla.checkboo.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String password;

    private String username;

    private String ltuidV2;

    private String ltokenV2;

    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void updateToken(String ltuidV2, String ltokenV2) {
        this.ltuidV2 = ltuidV2;
        this.ltokenV2 = ltokenV2;
    }
}
