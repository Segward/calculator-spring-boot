package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.HistoryDAO;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.responses.HistoryResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

  public HistoryResponse fetch(String jwt) throws Exception {
    String username = SecurityConfig.extractUsername(jwt);
    boolean exists = UserDAO.exists(username);
    if (!exists) {
      throw new Exception("User does not exist");
    }
    int userId = UserDAO.getUserId(username);
    ArrayList<String> history = HistoryDAO.fetch(userId);
    return new HistoryResponse(history);
  }
}
