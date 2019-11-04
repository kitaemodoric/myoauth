package kt.exercise.myoauth.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="access_token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id; //시퀀스 아이디
	
	@Column(name="token")
	private String token;
	
	@Column(name="refresh_token")
	private String refreshToken;
	
	@Column(name="member_id")
	private Long memberId;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="device_key")
	private String deviceKey;
	
	public AccessToken(String token, String refreshToken, Long memberId, String deviceKey, LocalDateTime createdAt) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.memberId = memberId;
		this.deviceKey = deviceKey;
		this.createdAt = createdAt;
	}
}
