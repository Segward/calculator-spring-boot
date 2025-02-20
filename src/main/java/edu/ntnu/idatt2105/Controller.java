package edu.ntnu.idatt2105;

import edu.ntnu.idatt2105.components.Calculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  Calculator calculator = new Calculator();

  @GetMapping("/calculate")
  public Result calculate(@RequestParam String operation) {
    double result = calculator.calculate(operation);
    return new Result(result);
  }

  static class Result {
    private final double result;

    public Result(double result) {
      this.result = result;
    }

    public double getMessage() {
      return result;
    }
  }
}