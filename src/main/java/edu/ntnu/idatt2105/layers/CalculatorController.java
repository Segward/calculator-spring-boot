package edu.ntnu.idatt2105.layers;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  Logger logger = Logger.getLogger(CalculatorController.class.getName());

  @GetMapping("/calculate")
  public Result calculate(@RequestParam String equation) {
    try {
      logger.info("Calculating operation: " + equation);
      String result = CalculatorService.calculate(equation);
      logger.info("Result: " + result);
      return new Result(result);
    } catch (Exception e) {
      throw new RuntimeException("Error calculating operation: " + equation, e);
    }
  }
}