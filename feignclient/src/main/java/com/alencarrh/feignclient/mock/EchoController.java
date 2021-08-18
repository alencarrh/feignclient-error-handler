package com.alencarrh.feignclient.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EchoController {

  @GetMapping("/mock/get")
  public ResponseEntity<Void> get() {
    return get(200);
  }

  @GetMapping("/mock/get/{status}")
  public ResponseEntity<Void> get(@PathVariable("status") final Integer status) {
    return ResponseEntity.status(status).build();
  }
}
