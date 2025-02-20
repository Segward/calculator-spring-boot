package edu.ntnu.idatt2105.layers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    @Test
    public void testBasicAddition() {
        String operation = "1+1";
        String result = CalculatorService.calculate(operation);
        assertEquals("2.0", result);
    }

    @Test
    public void testBasicSubtraction() {
        String operation = "1-1";
        String result = CalculatorService.calculate(operation);
        assertEquals("0.0", result);
    }

    @Test
    public void testBasicMultiplication() {
        String operation = "2*2";
        String result = CalculatorService.calculate(operation);
        assertEquals("4.0", result);
    }

    @Test
    public void testBasicDivision() {
        String operation = "4/2";
        String result = CalculatorService.calculate(operation);
        assertEquals("2.0", result);
    }

    @Test
    public void testComplexOperation() {
        String operation = "1+2*3-4/2";
        String result = CalculatorService.calculate(operation);
        assertEquals("5.0", result);
    }

    @Test
    public void testComplexOperationWithNestedMultiplication() {
        String operation = "1+2*3-4/2*2*2";
        String result = CalculatorService.calculate(operation);
        assertEquals("-1.0", result);
    }

    @Test
    public void testComplexOperationWithNestedDivision() {
        String operation = "1+2*3-4/2/2";
        String result = CalculatorService.calculate(operation);
        assertEquals("6.0", result);
    }
}
