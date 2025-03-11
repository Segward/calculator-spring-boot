package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.CalculateRequest;
import edu.ntnu.idatt2105.responses.CalculateResponse;
import edu.ntnu.idatt2105.services.CalculateService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculateController {

  @Autowired CalculateService calculateService;

  Logger logger = Logger.getLogger(CalculateController.class.getName());

  @RequestMapping(value = "/calculate", method = RequestMethod.POST)
  public ResponseEntity<CalculateResponse> calculate(@RequestBody CalculateRequest request) {
    try {
      CalculateResponse response = calculateService.calculate(request);
      logger.info("Calculation performed: " + response.getResult());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}