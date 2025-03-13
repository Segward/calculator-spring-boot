package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.services.ContactService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

  @Autowired ContactService contactService;

  Logger logger = Logger.getLogger(ContactController.class.getName());

  @PostMapping("/contact")
  public ResponseEntity<Boolean> authenticate(
      @RequestParam String jwt,
      @RequestParam String name,
      @RequestParam String email,
      @RequestParam String message) {
    try {
      contactService.contact(jwt, name, email, message);
      logger.info("Contact request received: " + name + " " + email + " " + message);
      return ResponseEntity.ok(true);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
