package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.dto.EmployerDto;
import com.datauser.goalmanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployee() {
        log.info(">> UserController.getAllEmployee enter");
        List<EmployeeDto> resultList = this.userService.getAllEmployee();
        log.info("<< UserController.getAllEmployee exit");
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @GetMapping("/employers")
    public ResponseEntity<?> getAllEmployer() {
        log.info(">> UserController.getAllEmployer enter");
        List<EmployerDto> resultList = this.userService.getAllEmployer();
        log.info("<< UserController.getAllEmployer exit");
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }
}
