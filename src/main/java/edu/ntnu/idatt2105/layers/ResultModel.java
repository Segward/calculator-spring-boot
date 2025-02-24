package edu.ntnu.idatt2105.layers;

public class ResultModel {
  private String equation;
  private String result;

  public ResultModel(String equation, String result) {
    this.equation = equation;
    this.result = result;
  }

  public String getEquation() {
    return equation;
  }

  public void setEquation(String equation) {
    this.equation = equation;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}