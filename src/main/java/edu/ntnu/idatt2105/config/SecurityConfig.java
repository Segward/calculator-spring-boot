package edu.ntnu.idatt2105.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

  private static final long EXPIRY = 1000 * 60;
  private static final String SECRET = "secret";

  public static Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
  }

  public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public static String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public static Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public static String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  public static String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  public static boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public static boolean validateToken(String token, String username) {
    return extractUsername(token).equals(username) && !isTokenExpired(token);
  }

  public static byte[] generateSalt() {
    byte[] salt = new byte[16];
    new SecureRandom().nextBytes(salt);
    return salt;
  }

  public static String hashPassword(String password, byte[] salt) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      byte[] hashedPassword = md.digest(password.getBytes());
      return Base64.getEncoder().encodeToString(hashedPassword);
    } catch (Exception e) {
      System.out.println("Error hashing password: " + e.getMessage());
      return password;
    }
  }

  public static boolean verifyPassword(String password, byte[] salt, String hashedPassword) {
    return hashPassword(password, salt).equals(hashedPassword);
  }
}
