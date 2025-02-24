package edu.ntnu.idatt2105.layers;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Convenience controller which automates controller and responsebody
// @Controller Marks the class as a Spring MVC controller
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {

  @Autowired private CalculatorService service; // Dependency injection (DI) of CalculatorService
  // Use DI instead of IoC (Inversion of Control) to make the code more testable, flexable, scalable
  // and maintainable. IoC is a design principle where the control of objects is transferred to a
  // container or framework

  Logger logger = Logger.getLogger(CalculatorController.class.getName());

  @GetMapping("/calculate")
  // @ResponseBody annotation tells a controller that the object returned is automatically
  // serialized into JSON and passed back into the HttpResponse object.
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