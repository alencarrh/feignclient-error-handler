package com.alencarrh.feignclienterrorhandler.config;

import static java.util.Objects.isNull;

import com.alencarrh.feignclienterrorhandler.exception.FeignClientErrorException;
import feign.Logger;
import feign.Response;
import feign.Retryer;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.Reader;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = FeignConfiguration.FEIGN_MAIN_PACKAGE)
@RequiredArgsConstructor
public class FeignConfiguration {

  public static final String FEIGN_MAIN_PACKAGE = "com.alencarrh.feignclienterrorhandler";

  private final FeignExceptionHandlerConfiguration.FeignExceptionHandlers handlers;

  @Bean
  Retryer retryer() {
    return Retryer.NEVER_RETRY;
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public ErrorDecoder errorDecoder() {
    return (methodKey, response) -> {
      final byte[] body = getResponseBody(response.body());
      final HttpStatus status = HttpStatus.valueOf(response.status());

      final FeignClientErrorException exception =
          new FeignClientErrorException(status, status.name(), body, Charset.defaultCharset());

      return handlers
          .getHandlers()
          .getOrDefault(methodKey, () -> error -> error)
          .get()
          .apply(exception);
    };
  }

  private static byte[] getResponseBody(final Response.Body body) {
    if (isNull(body)) {
      return new byte[] {};
    }

    try {

      final Reader reader = body.asReader(Charset.defaultCharset());
      return Util.toString(reader).getBytes();

    } catch (final Exception e) {
      return new byte[] {};
    }
  }
}
