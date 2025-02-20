package edu.ntnu.idatt2105.layers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

  @GetMapping("/calculate")
  public Result calculate(@RequestParam String operation) {
    try {
      String result = CalculatorService.calculate(operation);
      return new Result(result);
    } catch (Exception e) {
      throw new RuntimeException("Error calculating operation: " + operation, e);
    }
  }
}