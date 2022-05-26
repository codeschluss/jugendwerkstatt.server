package app.wooportal.server.core.security.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class AuthorizationService {
  
  @Autowired
  private HttpServletRequest request;
  
  public UserEntity getCurrentUser() {
    return null;
  }
  
  public boolean isAdmin(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isAdmin();
    }
    return false;
  }
  
  public boolean isApproved(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }
  
  public boolean isSuperviser(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isSuperviser();
    }
    return false;
  }
  
  public boolean isVerified(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }
  
}
