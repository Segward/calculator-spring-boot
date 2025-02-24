package edu.ntnu.idatt2105.layers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

  CalculatorService calculatorService = new CalculatorService();

  @Test
  public void testBasicAddition() {
    String operation = "1+1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("2", result.getResult());
  }

  @Test
  public void testBasicSubtraction() {
    String operation = "1-1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("0", result.getResult());
  }

  @Test
  public void testBasicMultiplication() {
    String operation = "2*2";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("4", result.getResult());
  }

  @Test
  public void testBasicDivision() {
    String operation = "4/2";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("2", result.getResult());
  }

  @Test
  public void testBasicPemdas() {
    String operation = "1+2*3";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("7", result.getResult());
  }

  @Test
  public void testComplexPemdas() {
    String operation = "1+2*3/2-1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("3", result.getResult());
  }

  @Test
  public void testComplexPemdasWithDecimals() {
    String operation = "1.5+2.5*3/2-1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("4.25", result.getResult());
  }

  public void testNegativeNumbers() {
    String operation = "-1+2*3/2-1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("1", result.getResult());
  }

  @Test
  public void testNegativeNumbersWithDecimals() {
    String operation = "-1.5+2.5*3/2-1";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("1.25", result.getResult());
  }

  public void testComplexFinalBossTest() {
    String operation = "-1.5+2.5*3/2-1+2*3/2-1.5";
    ResultModel result = calculatorService.calculate(operation);
    assertEquals("2.75", result.getResult());
  }
}
