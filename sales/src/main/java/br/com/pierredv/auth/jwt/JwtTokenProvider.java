package br.com.pierredv.auth.jwt;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtTokenProvider {
	
	@Autowired
	@Qualifier("UserService")
	private UserDetailsService userDetailsService;
	
	@Value("${security.}")
	private String secreteKey;
	
	@Value("{security.jwt.token.secret-key}")
	private long expire;
	
	@PostConstruct
	protected void init() {
		secreteKey = Base64.getEncoder().encodeToString(secreteKey.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		 Claims claims = Jwts.claims().setSubject(username);
		 claims.put("roles", roles);
		 
		 Date now = new Date();
		 Date validate = new Date(now.getTime() + expire);
		 
		 return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validate)
				 .signWith(SignatureAlgorithm.HS256, secreteKey).compact();
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	private String getUserName(String token) {
		return Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7,bearerToken.length());
	}
		
	return null;
 }
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

}
