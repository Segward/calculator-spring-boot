package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.AuthenticateRequest;
import edu.ntnu.idatt2105.responses.AuthenticateResponse;
import edu.ntnu.idatt2105.services.AuthenticateService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticateController {

  @Autowired AuthenticateService authenticateService;

  Logger logger = Logger.getLogger(AuthenticateController.class.getName());

  @RequestMapping(value = "/token", method = RequestMethod.POST)
  public ResponseEntity<AuthenticateResponse> authenticate(
      @RequestBody AuthenticateRequest request) {
    try {
      AuthenticateResponse response = authenticateService.getToken(request);
      logger.info("Authentication performed: " + response.getJwt());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
