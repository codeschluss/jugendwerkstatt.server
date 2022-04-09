package app.wooportal.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VerificationInvalidException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public VerificationInvalidException(String... params) {
    super(String.format("Verification invalid, params: %2$s", 
        params != null
          ? Stream.of(params).map(p -> p.toString()).collect(Collectors.joining(", "))
          : ""));
  }
}
