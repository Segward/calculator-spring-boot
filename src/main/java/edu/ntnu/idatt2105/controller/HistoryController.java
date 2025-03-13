package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.models.HistoryResponse;
import edu.ntnu.idatt2105.services.HistoryService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HistoryController {

  @Autowired HistoryService historyService;

  Logger logger = Logger.getLogger(HistoryController.class.getName());

  @GetMapping("/fetch")
  public ResponseEntity<HistoryResponse> calculate(@RequestParam String jwt) {
    try {
      HistoryResponse response = historyService.fetch(jwt);
      logger.info("History fetched: " + response.getEquations() + " " + response.getResults());
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      logger.severe(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}