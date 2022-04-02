package app.wooportal.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvalidTokenException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  
  public InvalidTokenException(String message, String... params) {
    super(String.format("Invalid token: %1$s, params: %2$s", 
        message,
        params != null 
          ? Stream.of(params).map(p -> p.toString()).collect(Collectors.joining(", "))
          : ""));
  }
}
