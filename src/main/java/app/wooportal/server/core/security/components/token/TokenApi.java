package app.wooportal.server.core.security.components.token;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.services.JwtUserDetailsService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class TokenApi {
  
  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;
  
  private final JwtUserDetailsService userDetailService;
  
  @Autowired
  private HttpServletRequest request;

  public TokenApi(
      TokenService tokenService, 
      JwtUserDetailsService userDetailService) {
    
    this.authenticationManager = new ProviderManager(new DaoAuthenticationProvider());
    this.tokenService = tokenService;
    this.userDetailService = userDetailService;
  }
  
  @GraphQLMutation(name = "createToken")
  public TokenDto createToken(
      String username,
      String password) {
    var jwtUserDetails = (JwtUserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        username, password, Collections.emptyList())).getPrincipal();
    
    var accessToken = tokenService.createAccessToken(jwtUserDetails);
    var refreshToken = tokenService.createRefreshToken(jwtUserDetails);

    return new TokenDto(accessToken, refreshToken);
  }

  @GraphQLMutation(name = "refreshToken")
  public TokenDto refreshToken() throws Exception {
    var refreshToken = request.getHeader("Authorization");
    tokenService.verifyRefresh(refreshToken);

    var username = tokenService.extractUsername(refreshToken);
    var accessToken = tokenService.createAccessToken(userDetailService.loadUserByUsername(username));

    return new TokenDto(accessToken, refreshToken);
  }

}
