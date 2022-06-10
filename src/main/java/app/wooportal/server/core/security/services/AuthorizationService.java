package app.wooportal.server.core.security.services;

import java.util.Collections;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.token.TokenService;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class AuthorizationService {
  
  private final TokenService tokenService;

  private final JwtUserDetailsService userDetailsService;

  @Lazy
  public AuthorizationService(TokenService tokenService, JwtUserDetailsService userDetailsService) {
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }

  public Optional<UserEntity> getAuthenticatedUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return Optional.of(jwtUserDetails.getUser());
    }
    return Optional.empty();
  }
  
  public Optional<UserEntity> getUserFromToken(String jwtToken) {
    var userDetails = retrieveUserDetailsFromToken(jwtToken);
    return userDetails.isPresent()
      ? Optional.ofNullable(userDetails.get().getUser())
      : Optional.empty();
  }
  
  public Optional<UsernamePasswordAuthenticationToken> getAuthenticationToken(HttpServletRequest request) {
    if (request != null) {
      try {
        var jwtToken = request.getHeader("Authorization");
        var userDetails = retrieveUserDetailsFromToken(jwtToken);
        if (userDetails.isPresent()) {
          return Optional.of(new UsernamePasswordAuthenticationToken(
              userDetails.get(), null, Collections.emptyList())); 
        }
      } catch (Exception ignored) { }
    }
    return Optional.empty();
  }
  
  private Optional<JwtUserDetails> retrieveUserDetailsFromToken(String jwtToken) {
    if (jwtToken != null) {
      var username = tokenService.verifyAccess(jwtToken).getSubject();
      if (username != null) {
        return Optional.ofNullable(userDetailsService.loadUserByUsername(username));
      }
    }
    return Optional.empty();
  }

  public boolean isAdmin(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isAdmin();
    }
    return false;
  }

  public boolean isApproved(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

  public boolean isSuperviser(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isSuperviser();
    }
    return false;
  }

  public boolean isVerified(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

}
