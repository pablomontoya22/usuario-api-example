package app.handlers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import app.models.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTHandler {
	@Value("${jwt.expiration}")
	private Long expirationJWT;
	@Value("${jwt.secret.key}")
	private String secretKey;

	public String create(final User entity) {
		return JWT.create().withIssuer("Users API").withSubject(entity.getName()).withClaim("email", entity.getEmail())
			.withClaim("id", entity.getId().toString()).withIssuedAt(new Date())
			.withExpiresAt(new Date(System.currentTimeMillis() + expirationJWT * 1000))
			.withJWTId(UUID.randomUUID().toString()).withNotBefore(new Date(System.currentTimeMillis() + 1000L))
			.sign(Algorithm.HMAC256(secretKey));
	}

	public DecodedJWT decode(final String jwtToken) {
		try {
			return JWT.decode(jwtToken);
		} catch (JWTVerificationException e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
