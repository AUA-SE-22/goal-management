package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.OperationalMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/error")
@CrossOrigin(origins = "*")
public class ErrorController {

    @GetMapping("/access-denied-response")
    public ResponseEntity<?> errorMessage() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new OperationalMessage("You are not allowed to view this page"));
    }
}
