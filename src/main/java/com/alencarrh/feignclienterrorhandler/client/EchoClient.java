package com.alencarrh.feignclienterrorhandler.client;

import com.alencarrh.feignclienterrorhandler.annotation.CustomFeignException;
import com.alencarrh.feignclienterrorhandler.exception.handler.GetExceptionHandler;
import com.alencarrh.feignclienterrorhandler.exception.handler.PostExceptionHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "echo-client", url = "${client.echo}")
public interface EchoClient {

  @GetMapping("/get")
  void get();

  @GetMapping("/get/{status}")
  void getWithStatus(@PathVariable("status") final Integer status);

  @CustomFeignException(handler = GetExceptionHandler.class)
  @GetMapping("/get/{status}")
  void getWithStatusAndHandler(@PathVariable("status") final Integer status);

  @PostMapping("/post/{status}")
  Object postWithStatus(
      @PathVariable("status") final Integer status, @RequestBody final Object request);

  @CustomFeignException(handler = PostExceptionHandler.class)
  @PostMapping("/post/{status}")
  Object postWithStatusAndHandler(
      @PathVariable("status") final Integer status, @RequestBody final Object request);
}
