package edu.ntnu.idatt2105.models;

public class AuthenticateResponse {

  private final String jwt;

  public AuthenticateResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }
}
