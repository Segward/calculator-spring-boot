package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.model.HistoryRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoryController {

  @GetMapping("/fetch-history")
  public String calculate(@RequestBody HistoryRequest request) {
    return "Calculator";
  }
}