package com.alencarrh.feignclient.exception.handler;

import com.alencarrh.feignclient.exception.EchoErrorException;
import com.alencarrh.feignclient.exception.FeignClientErrorException;
import com.alencarrh.feignclient.exception.MyPrettyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/** TODO rethink these handlers exceptions to have a better and simpler example of how this works */
@Slf4j
@Component
@RequiredArgsConstructor
public class PostExceptionHandler implements Function<FeignClientErrorException, RuntimeException> {

  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public RuntimeException apply(final FeignClientErrorException e) {

    //    if (e.getStatusCode().is5xxServerError()) {
    //      return MyPrettyException.builder()
    //          .status(e.getRawStatusCode())
    //          .code("SOME_CODE_FOR_5xx_GET_ERROR")
    //          .message("Some pretty message for 5xx error on server side GET error")
    //          .build();
    //    }

    final EchoErrorException exception =
        objectMapper.readValue(e.getResponseBodyAsString(), EchoErrorException.class);

    if (e.getStatusCode().is4xxClientError()) {
      return MyPrettyException.builder()
          .status(e.getRawStatusCode())
          .code("SOME_CODE_FOR_!5xx_GET_ERROR")
          .message("Some pretty message for 5xx error on server side GET error")
          .build();
    }

    log.warn(exception.getCode() + " - " + exception.getMessage());
    return MyPrettyException.builder()
        .status(e.getRawStatusCode())
        .code(exception.getCode())
        .message(exception.getMessage())
        .build();
  }
}
