package br.com.delivery.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesJwt {

	@Value("${security.jwt.secret}")
	private String jwtSecret;
	
	@Value("${security.jwt.expiration}")
	private Integer jwtExpirationInMinutes;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public Integer getJwtExpirationInMinutes() {
		return jwtExpirationInMinutes;
	}

	public void setJwtExpirationInMinutes(Integer jwtExpirationInMinutes) {
		this.jwtExpirationInMinutes = jwtExpirationInMinutes;
	}

}
