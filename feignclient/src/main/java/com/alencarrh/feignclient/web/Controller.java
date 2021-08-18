package com.alencarrh.feignclient.web;

import com.alencarrh.feignclient.client.EchoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final EchoClient echoClient;

  @GetMapping("/get")
  public void get() {
    echoClient.get();
  }

  @GetMapping("/get/{status}")
  public void getStatus(@PathVariable final Integer status) {
    echoClient.getWithStatus(status);
  }
}
