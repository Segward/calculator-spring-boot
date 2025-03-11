package edu.ntnu.idatt2105.requests;

public class HistoryRequest {
  private String jwt;

  public HistoryRequest(String jwt, String username) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
