package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.model.AuthenticateRequest;
import edu.ntnu.idatt2105.model.AuthenticateResponse;
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
    return ResponseEntity.ok(authenticateService.getToken(request));
  }

  @RequestMapping(value = "/valid", method = RequestMethod.GET)
  public ResponseEntity<Boolean> verify(
      @RequestBody AuthenticateRequest request, @RequestBody String jwt) {
    return ResponseEntity.ok(authenticateService.getValidity(request, jwt));
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<AuthenticateResponse> register(@RequestBody AuthenticateRequest request) {
    return ResponseEntity.ok(authenticateService.getToken(request));
  }
}
