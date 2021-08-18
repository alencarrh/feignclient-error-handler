package com.alencarrh.feignclient.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPrettyException extends RuntimeException {

  private final int status;
  private final String code;
  private final String message;
  private final Map<String, Object> details;
}
