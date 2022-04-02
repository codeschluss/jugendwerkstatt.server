package app.wooportal.server.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import app.wooportal.server.core.security.components.token.TokenService;
import app.wooportal.server.core.security.filter.JwtAuthorizationFilter;
import app.wooportal.server.core.security.services.JwtUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

  private final JwtUserDetailsService userDetailsService;

  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final TokenService tokenService;

  public ApplicationSecurity(
      JwtUserDetailsService jwtUserDetailsService,
      BCryptPasswordEncoder encoder, 
      TokenService tokenService) {
    this.userDetailsService = jwtUserDetailsService;
    this.bcryptPasswordEncoder = encoder;
    this.tokenService = tokenService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService).passwordEncoder(bcryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .cors().and()
    .csrf().disable()
    .headers().frameOptions().sameOrigin()
      .and()
    .addFilter(jwtAuthorizationFilter())
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  @Profile("development")
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOriginPattern(CorsConfiguration.ALL);
    config.addAllowedHeader(CorsConfiguration.ALL);
    config.addAllowedMethod(CorsConfiguration.ALL);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
    return new JwtAuthorizationFilter(
        authenticationManager(), 
        userDetailsService, 
        tokenService);
  }
  
  @Bean
  public AuthenticationManager getAuthManager() {
    var provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(bcryptPasswordEncoder);
    return new ProviderManager(provider);
  }
}
