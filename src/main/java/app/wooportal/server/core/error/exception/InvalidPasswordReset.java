package app.wooportal.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvalidPasswordReset extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidPasswordReset(String... params) {
    super(String.format("Password reset invalid, params: %1$s", 
        params != null
          ? Stream.of(params).map(p -> p.toString()).collect(Collectors.joining(", "))
          : ""));
  }
}
