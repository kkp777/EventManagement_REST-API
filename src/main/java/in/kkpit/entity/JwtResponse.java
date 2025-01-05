package in.kkpit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
   

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
	private final String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}
	 
}