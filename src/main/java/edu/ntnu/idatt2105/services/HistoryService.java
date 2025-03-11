package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.HistoryDAO;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.requests.HistoryRequest;
import edu.ntnu.idatt2105.responses.HistoryResponse;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

  public HistoryResponse fetch(HistoryRequest request) throws Exception {
    String username = SecurityConfig.extractUsername(request.getJwt());
    boolean exists = UserDAO.exists(username);
    if (!exists) {
      return new HistoryResponse(new ArrayList<>());
    }
    int userId = UserDAO.getUserId(username);
    ArrayList<String> history = HistoryDAO.fetch(userId);
    return new HistoryResponse(history);
  }
}
