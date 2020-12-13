package com.alencarrh.feignclienterrorhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EchoErrorException extends RuntimeException {

  private final String code;
  private final String message;
}
