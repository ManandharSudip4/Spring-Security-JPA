package com.mstech.springsecurityjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

  @GetMapping("/")
  public String home() {
    return ("<h1>Hola! Comos Estas?</h1>");
  }

  @GetMapping("/user")
  public String user() {
    return ("<h1>Hola, User! Comos Estas?</h1>");
  }

  @GetMapping("/admin")
  public String admin() {
    return ("<h1>Hola, Admino! Comos Estas?</h1>");
  }
}
