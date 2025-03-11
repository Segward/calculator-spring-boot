package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.responses.AuthenticateResponse;
import edu.ntnu.idatt2105.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticateController {

  @Autowired AuthenticateService authenticateService;

  @GetMapping("/token")
  public ResponseEntity<AuthenticateResponse> authenticate(
      @RequestParam String username, @RequestParam String password) {
    try {
      AuthenticateResponse response = authenticateService.getToken(username, password);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
