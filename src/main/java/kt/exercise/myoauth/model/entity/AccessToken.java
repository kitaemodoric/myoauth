package kt.exercise.myoauth.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="oauth_access_token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

	@Id
	@Column(name = "authentication_id")
	private String authId;

	@Column(name = "token_id")
	private String tokenId;

	@Lob
	private byte[] token;
	@Lob
	private byte[] authentication;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "client_id")
	private String ClientId;

	@Column(name = "refresh_token")
	private String refreshToken;


}
