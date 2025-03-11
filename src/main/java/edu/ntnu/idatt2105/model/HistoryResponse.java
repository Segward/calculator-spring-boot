package edu.ntnu.idatt2105.model;

import java.util.ArrayList;

public class HistoryResponse {

  private final ArrayList<String> history;

  public HistoryResponse(ArrayList<String> history) {
    this.history = history;
  }

  public ArrayList<String> getHistory() {
    return history;
  }
}
