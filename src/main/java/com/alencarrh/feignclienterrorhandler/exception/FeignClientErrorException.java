package com.alencarrh.feignclienterrorhandler.exception;

import java.io.Serial;
import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class FeignClientErrorException extends HttpStatusCodeException {

  @Serial private static final long serialVersionUID = 302149617135388212L;

  public FeignClientErrorException(
      final HttpStatus statusCode,
      final String statusText,
      final byte[] responseBody,
      final Charset responseCharset) {
    super(statusCode, statusText, responseBody, responseCharset);
  }
}
