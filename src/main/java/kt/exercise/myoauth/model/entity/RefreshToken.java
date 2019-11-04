package kt.exercise.myoauth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "oauth_refresh_token")
public class RefreshToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Lob
    private byte[] token;

    @Lob
    private byte[] authentication;


}
