package edu.ntnu.idatt2105;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Calculator {

    @GetMapping("/add")
    public Result add(@RequestParam int a, @RequestParam int b) {
        return new Result(a + b);
    }

    @GetMapping("/subtract")
    public Result subtract(@RequestParam int a, @RequestParam int b) {
        return new Result(a - b);
    }

    @GetMapping("/multiply")
    public Result multiply(@RequestParam int a, @RequestParam int b) {
        return new Result(a * b);
    }

    @GetMapping("/divide")
    public Result divide(@RequestParam int a, @RequestParam int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return new Result(a / b);
    }

    static class Result {
        private final int result;

        public Result(int result) {
            this.result = result;
        }

        public int getResult() {
            return result;
        }
    }
}