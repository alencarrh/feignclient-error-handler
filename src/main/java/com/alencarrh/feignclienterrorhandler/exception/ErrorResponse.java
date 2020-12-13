package com.alencarrh.feignclienterrorhandler.exception;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

  private final String code;
  private final String message;
  private final Map<String, Object> details;

  public static ErrorResponse of(MyPrettyException ex) {
    return ErrorResponse.builder()
        .code(ex.getCode())
        .message(ex.getMessage())
        .details(ex.getDetails())
        .build();
  }
}
