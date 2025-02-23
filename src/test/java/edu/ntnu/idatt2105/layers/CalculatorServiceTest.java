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
    public void testBasicPemdas() {
        String operation = "1+2*3";
        String result = CalculatorService.calculate(operation);
        assertEquals("7.0", result);
    }

    @Test
    public void testComplexPemdas() {
        String operation = "1+2*3/2-1";
        String result = CalculatorService.calculate(operation);
        assertEquals("3.0", result);
    }

    @Test
    public void testComplexPemdasWithDecimals() {
        String operation = "1.5+2.5*3/2-1";
        String result = CalculatorService.calculate(operation);
        assertEquals("4.25", result);
    }

    public void testNegativeNumbers() {
        String operation = "-1+2*3/2-1";
        String result = CalculatorService.calculate(operation);
        assertEquals("1.0", result);
    }

    @Test
    public void testNegativeNumbersWithDecimals() {
        String operation = "-1.5+2.5*3/2-1";
        String result = CalculatorService.calculate(operation);
        assertEquals("1.25", result);
    }

    public void testComplexFinalBossTest() {
        String operation = "-1.5+2.5*3/2-1+2*3/2-1.5";
        String result = CalculatorService.calculate(operation);
        assertEquals("2.75", result);
    }
    
}
