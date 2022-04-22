package app.wooportal.server.core.security.components.token;

import java.util.Arrays;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.security.JwtConfiguration;
import app.wooportal.server.core.security.JwtUserDetails;

@Service
public class TokenService {
  
  private JwtConfiguration securityConfig;

  public TokenService(
      JwtConfiguration securityConfig) throws Exception {
    this.securityConfig = securityConfig;
  }

  public String createAccessToken(JwtUserDetails jwtUserDetails) {
    return JWT.create().withSubject(jwtUserDetails.getUsername())
        .withClaim(securityConfig.getClaimUserid(), jwtUserDetails.getUser().getId())
        .withArrayClaim(securityConfig.getClaimRoles(), jwtUserDetails.getRoles())
        .withArrayClaim(securityConfig.getClaimScopes(), 
            new String[] {securityConfig.getScopeAccess()})
        .withExpiresAt(
            new Date(
                System.currentTimeMillis() + securityConfig.getExpirationTimeAccess().toMillis()))
        .sign(Algorithm.HMAC512(securityConfig.getSecret()));
  }

  public String createRefreshToken(JwtUserDetails jwtUserDetails) {
    return JWT.create().withSubject(jwtUserDetails.getUsername())
        .withArrayClaim(securityConfig.getClaimScopes(), 
            new String[] {securityConfig.getScopeRefresh()})
        .withExpiresAt(
            new Date(
                System.currentTimeMillis() + securityConfig.getExpirationTimeRefresh().toMillis()))
        .sign(Algorithm.HMAC512(securityConfig.getSecret()));
  }
  
  public void verifyAccess(String token) throws InvalidTokenException {
    if (!verifyWithScope(token, securityConfig.getScopeAccess())) {
      throw new InvalidTokenException(
          "Token must contain scope", securityConfig.getScopeAccess());
    }
  }
  
  public void verifyRefresh(String token) throws InvalidTokenException {
    if (!verifyWithScope(token, securityConfig.getScopeRefresh())) {
      throw new InvalidTokenException(
          "Token must contain scope", securityConfig.getScopeRefresh());
    }
  }
  
  private boolean verifyWithScope(String token, String requiredScope) {
    String[] scopes = verify(token).getClaim(securityConfig.getClaimScopes()).asArray(String.class);
    return Arrays.asList(scopes).stream()
        .anyMatch(scope -> scope.equals(requiredScope));
  }
 
  public String extractUsername(String token) {
    return verify(token).getSubject();
  }
  
  public DecodedJWT verify(String token) {
    return JWT
      .require(Algorithm.HMAC512(securityConfig.getSecret()))
      .build()
      .verify(token.replace("Bearer ", ""));
  }

}
