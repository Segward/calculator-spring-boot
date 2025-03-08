package edu.ntnu.idatt2105.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ntnu.idatt2105.config.SecurityConfig;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculateController {

  @Autowired
  SecurityConfig securityConfig;

  @GetMapping("/calculate")
  public String calculate(@RequestBody String request) {
    return "Calculator";
  }
}