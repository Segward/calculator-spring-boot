package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.AuthenticateRequest;
import edu.ntnu.idatt2105.responses.AuthenticateResponse;
import edu.ntnu.idatt2105.services.AuthenticateService;
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

  @RequestMapping(value = "/token", method = RequestMethod.GET)
  public ResponseEntity<AuthenticateResponse> authenticate(
      @RequestBody AuthenticateRequest request) {
    try {
      AuthenticateResponse response = authenticateService.getToken(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
