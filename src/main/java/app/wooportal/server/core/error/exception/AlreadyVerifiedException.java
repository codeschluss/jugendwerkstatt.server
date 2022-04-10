package app.wooportal.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlreadyVerifiedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public AlreadyVerifiedException(String... params) {
    super(String.format("Already verified, params: %1$s", 
        params != null
          ? Stream.of(params).map(p -> p.toString()).collect(Collectors.joining(", "))
          : ""));
  }
}
