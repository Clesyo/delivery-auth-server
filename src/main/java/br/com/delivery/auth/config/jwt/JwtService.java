package br.com.delivery.auth.config.jwt;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.delivery.auth.config.PropertiesJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Autowired
	private PropertiesJwt jwt;

	public String generateToken(UserDetails user) throws UnsupportedEncodingException {
		Long expiracao = Long.valueOf(jwt.getJwtExpirationInMinutes());
		LocalDateTime horaExpiracao = LocalDateTime.now().plusMinutes(expiracao);
		Instant instant = horaExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		
		String secretUtf8 = new String(jwt.getJwtSecret().getBytes(), "UTF-8");
		return Jwts.builder().setIssuer("Delivery API").setSubject(user.getUsername()).setExpiration(date)
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, secretUtf8)
				.compact();
	}

	private Claims obtainClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(jwt.getJwtSecret()).parseClaimsJws(token).getBody();
	}

	public String obtainLoginUser(String token) throws ExpiredJwtException {
		return (String) obtainClaims(token).getSubject();
	}

	public boolean tokenValid(String token) {
		try {
			Claims claims = obtainClaims(token);
			Date dateExpiraton = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiraton.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

//	public String generateToken(Authentication authenticate) throws UnsupportedEncodingException {
//		User user = (User) authenticate.getPrincipal();
//		return generateToken(user);
//	}
}
