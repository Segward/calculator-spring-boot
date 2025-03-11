package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.requests.HistoryRequest;
import edu.ntnu.idatt2105.responses.HistoryResponse;
import edu.ntnu.idatt2105.services.HistoryService;
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

  @GetMapping("/fetch")
  public ResponseEntity<HistoryResponse> calculate(@RequestBody HistoryRequest request) {
    try {
      HistoryResponse response = historyService.fetch(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}