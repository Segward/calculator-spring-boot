package edu.ntnu.idatt2105.models;

import java.util.ArrayList;

public class HistoryResponse {

  private final ArrayList<String> equations;
  private final ArrayList<String> results;

  public HistoryResponse(ArrayList<String> equations, ArrayList<String> results) {
    this.equations = equations;
    this.results = results;
  }

  public ArrayList<String> getEquations() {
    return equations;
  }

  public ArrayList<String> getResults() {
    return results;
  }
}
