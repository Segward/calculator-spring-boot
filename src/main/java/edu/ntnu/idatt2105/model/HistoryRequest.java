package edu.ntnu.idatt2105.model;

public class HistoryRequest {
  private String jwt;

  public HistoryRequest(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
