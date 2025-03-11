package edu.ntnu.idatt2105.requests;

public class CalculateRequest {

  private String jwt;
  private String action;

  public CalculateRequest(String jwt, String action) {
    this.jwt = jwt;
    this.action = action;
  }

  public String getJwt() {
    return jwt;
  }

  public String getAction() {
    return action;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
