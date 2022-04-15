package app.wooportal.server.core.security.components.token;

import java.util.Collections;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.services.JwtUserDetailsService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class TokenApi {

  private final AuthenticationManager authManager;

  private final TokenService tokenService;

  private final JwtUserDetailsService userDetailService;

  public TokenApi(AuthenticationManager authManager, TokenService tokenService,
      JwtUserDetailsService userDetailService) {

    this.authManager = authManager;
    this.tokenService = tokenService;
    this.userDetailService = userDetailService;
  }

  @GraphQLMutation(name = "createToken")
  public TokenDto createToken(String username, String password) {
    try {
      var jwtUserDetails = (JwtUserDetails) authManager
          .authenticate(
              new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()))
          .getPrincipal();

      var accessToken = tokenService.createAccessToken(jwtUserDetails);
      var refreshToken = tokenService.createRefreshToken(jwtUserDetails);

      return new TokenDto(accessToken, refreshToken);
    } catch (Exception e) {
      throw new BadCredentialsException(password);
    }
  }

  @GraphQLMutation(name = "refreshToken")
  public TokenDto refreshToken(String refreshToken) {
    tokenService.verifyRefresh(refreshToken);

    var username = tokenService.extractUsername(refreshToken);
    var accessToken =
        tokenService.createAccessToken(userDetailService.loadUserByUsername(username));

    return new TokenDto(accessToken, refreshToken);
  }

}
