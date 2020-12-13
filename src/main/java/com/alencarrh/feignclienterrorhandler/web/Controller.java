package com.alencarrh.feignclienterrorhandler.web;

import com.alencarrh.feignclienterrorhandler.client.EchoClient;
import com.alencarrh.feignclienterrorhandler.exception.EchoErrorException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final EchoClient echoClient;

  @GetMapping("/no-handler/{status}")
  public void getNoHandler(@PathVariable final Integer status) {
    echoClient.getWithStatus(status);
  }

  @GetMapping("/with-handler/{status}")
  public void getWithHandler(@PathVariable final Integer status) {
    echoClient.getWithStatusAndHandler(status);
  }

  @PostMapping("/no-handler/{status}")
  public void postNoHandler(@PathVariable final Integer status) {
    final EchoErrorException exception =
        EchoErrorException.builder()
            .code(RandomStringUtils.random(5))
            .message(UUID.randomUUID().toString())
            .build();

    echoClient.postWithStatus(status, exception);
  }

  @PostMapping("/with-handler/{status}")
  public void postWithHandler(@PathVariable final Integer status) {
    final EchoErrorException exception =
        EchoErrorException.builder()
            .code(RandomStringUtils.random(5))
            .message(UUID.randomUUID().toString())
            .build();

    echoClient.postWithStatusAndHandler(status, exception);
  }
}
