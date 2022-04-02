package app.wooportal.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BadParamsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BadParamsException(String message, Object... params) {
    super(String.format("Bad param: %1$s, params: %2$s", 
        message,
        params != null 
          ? Stream.of(params).map(p -> p.toString()).collect(Collectors.joining(", "))
          : ""));
  }
 
}
