package com.alencarrh.feignclient.config;

import static com.alencarrh.feignclient.config.FeignConfiguration.FEIGN_MAIN_PACKAGE;

import com.alencarrh.feignclient.annotation.CustomFeignException;
import com.alencarrh.feignclient.exception.FeignClientErrorException;
import com.alencarrh.feignclient.util.SpringContextUtil;
import feign.Feign;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionHandlerConfiguration {

  @Bean
  public FeignExceptionHandlers configureHandlers() {
    final Map<String, Supplier<Function<FeignClientErrorException, RuntimeException>>> handlers =
        new HashMap<>();

    new Reflections(FEIGN_MAIN_PACKAGE, new MethodAnnotationsScanner())
        .getMethodsAnnotatedWith(CustomFeignException.class)
        .forEach(method -> handlers.put(getKey(method), getHandler(method)));

    return new FeignExceptionHandlers(handlers);
  }

  private String getKey(final Method method) {
    return Feign.configKey(method.getDeclaringClass(), method);
  }

  /**
   * Supplier is returned because the Spring context is not initialize yet, so it won't find the
   * bean for the Handler.
   */
  private Supplier<Function<FeignClientErrorException, RuntimeException>> getHandler(
      final Method method) {
    return () ->
        SpringContextUtil.getBean(method.getAnnotation(CustomFeignException.class).handler());
  }

  @Getter
  @RequiredArgsConstructor
  static class FeignExceptionHandlers {
    private final Map<String, Supplier<Function<FeignClientErrorException, RuntimeException>>>
        handlers;
  }
}
