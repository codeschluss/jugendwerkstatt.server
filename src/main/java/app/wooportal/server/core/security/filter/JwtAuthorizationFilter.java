package app.wooportal.server.core.security.filter;

import app.wooportal.server.core.security.components.token.TokenService;
import app.wooportal.server.core.security.services.JwtUserDetailsService;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  /** The jwt user details service. */
  private final JwtUserDetailsService jwtUserDetailsService;

  private final TokenService tokenService;
  
  /**
   * Instantiates a new jwt authorization filter.
   *
   * @param authManager the auth manager
   * @param jwtUserDetailsService the jwt user details service
   * @param tokenService the token service
   */
  public JwtAuthorizationFilter(
      AuthenticationManager authManager,
      JwtUserDetailsService jwtUserDetailsService, 
      TokenService tokenService) {
    super(authManager);
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {

    String header = req.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    
    if (authentication != null) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      try {
        tokenService.verifyAccess(token);
      } catch (Exception e) {
        return null;
      }
      
      String username = tokenService.extractUsername(token);

      if (username != null) {
        return new UsernamePasswordAuthenticationToken(
            jwtUserDetailsService.loadUserByUsername(username), null, Collections.emptyList());
      }
    }
    return null;
  }
}
