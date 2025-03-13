package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.models.TokenResponse;
import edu.ntnu.idatt2105.services.AuthenticateService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

  @Autowired AuthenticateService authenticateService;

  Logger logger = Logger.getLogger(AuthenticateController.class.getName());

  @GetMapping("/token")
  public ResponseEntity<TokenResponse> authenticate(
      @RequestParam String username, @RequestParam String password) {
    try {
      TokenResponse response = authenticateService.getToken(username, password);
      logger.info("Token generated: " + response.getJwt());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/validate")
  public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
    try {
      boolean valid = authenticateService.validate(jwt);
      logger.info("Token is valid: " + valid);
      return ResponseEntity.ok(valid);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
