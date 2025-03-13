package edu.ntnu.idatt2105.models;

public class TokenResponse {

  private final String jwt;

  public TokenResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }
}
