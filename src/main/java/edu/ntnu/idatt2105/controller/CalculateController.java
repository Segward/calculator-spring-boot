package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.models.CalculateResponse;
import edu.ntnu.idatt2105.services.CalculateService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculateController {

  @Autowired CalculateService calculateService;

  Logger logger = Logger.getLogger(CalculateController.class.getName());

  @GetMapping("/calculate")
  public ResponseEntity<CalculateResponse> calculate(
      @RequestParam String jwt, @RequestParam String equation) {
    try {
      CalculateResponse response = calculateService.calculate(jwt, equation);
      logger.info("Calculation performed: " + response.getResult());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}