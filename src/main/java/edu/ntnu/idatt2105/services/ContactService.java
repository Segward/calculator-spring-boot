package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.ContactDAO;
import edu.ntnu.idatt2105.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

  public void contact(String jwt, String name, String email, String message) throws Exception {
    String username = SecurityConfig.extractUsername(jwt);
    boolean exists = UserDAO.exists(username);
    if (!exists) {
      throw new Exception("User does not exist");
    }
    int userId = UserDAO.getUserId(username);
    ContactDAO.insert(userId, name, email, message);
  }
}
