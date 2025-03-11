package edu.ntnu.idatt2105.model;

public class AuthenticateResponse {

  private final String jwt;

  public AuthenticateResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }
}
