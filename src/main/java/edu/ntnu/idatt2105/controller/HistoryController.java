package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.HistoryRequest;
import edu.ntnu.idatt2105.responses.HistoryResponse;
import edu.ntnu.idatt2105.services.HistoryService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoryController {

  @Autowired HistoryService historyService;

  Logger logger = Logger.getLogger(HistoryController.class.getName());

  @GetMapping("/fetch")
  public ResponseEntity<HistoryResponse> calculate(@RequestBody HistoryRequest request) {
    try {
      HistoryResponse response = historyService.fetch(request);
      logger.info("History fetched: " + response.getHistory());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}