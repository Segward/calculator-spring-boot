package edu.ntnu.idatt2105.layers;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {
  Logger logger = Logger.getLogger(CalculatorController.class.getName());

  @Autowired private CalculatorService service;

  @GetMapping("/calculate")
  public ResultModel calculate(@RequestParam String equation) {
    try {
      ResultModel result = service.calculate(equation);
      logger.info("Calculated operation: " + equation + " = " + result.getResult());
      return result;
    } catch (Exception e) {
      logger.severe("Error calculating operation: " + equation);
      return new ResultModel(equation, "Error");
    }
  }
}