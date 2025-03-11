package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.CalculateRequest;
import edu.ntnu.idatt2105.responses.CalculateResponse;
import edu.ntnu.idatt2105.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculateController {

  @Autowired CalculateService calculateService;

  @GetMapping("/calculate")
  public ResponseEntity<CalculateResponse> calculate(@RequestBody CalculateRequest request) {
    try {
      CalculateResponse response = calculateService.calculate(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}