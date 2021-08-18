package com.alencarrh.feignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "echo-client", url = "${client.echo}")
public interface EchoClient {

  @GetMapping("/get")
  void get();

  @GetMapping("/get/{status}")
  void getWithStatus(@PathVariable("status") final Integer status);
}
