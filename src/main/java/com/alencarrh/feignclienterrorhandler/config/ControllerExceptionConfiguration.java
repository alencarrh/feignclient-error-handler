package com.alencarrh.feignclienterrorhandler.config;

import com.alencarrh.feignclienterrorhandler.exception.ErrorResponse;
import com.alencarrh.feignclienterrorhandler.exception.MyPrettyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionConfiguration extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = MyPrettyException.class)
  protected ResponseEntity<ErrorResponse> handler(MyPrettyException ex) {
    return ResponseEntity.status(ex.getStatus()).body(ErrorResponse.of(ex));
  }
}
