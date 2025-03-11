package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.HistoryDAO;
import edu.ntnu.idatt2105.model.HistoryRequest;
import edu.ntnu.idatt2105.model.HistoryResponse;
import java.util.ArrayList;

public class HistoryServer {

  public HistoryResponse fetch(HistoryRequest request) throws Exception {
    SecurityConfig securityConfig = new SecurityConfig();
    String username = securityConfig.extractUsername(request.getJwt());
    ArrayList<String> history = HistoryDAO.extract(username);
    return new HistoryResponse(history);
  }
}
