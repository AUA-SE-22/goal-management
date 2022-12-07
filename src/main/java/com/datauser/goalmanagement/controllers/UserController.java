package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.dto.EmployerDto;
import com.datauser.goalmanagement.dto.OperationalMessage;
import com.datauser.goalmanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        log.info(">> UserController.getEmployeeById enter");
        EmployeeDto result = this.userService.findEmployeeById(id);
        if (result == null) {
            log.info("<< UserController.getEmployeeById exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new OperationalMessage("Employee with id: " + id + " not found"));
        }
        log.info("<< UserController.getEmployeeById exit");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/employers")
    public ResponseEntity<?> getAllEmployer() {
        log.info(">> UserController.getAllEmployer enter");
        List<EmployerDto> resultList = this.userService.getAllEmployer();
        log.info("<< UserController.getAllEmployer exit");
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @GetMapping("/employers/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable("id") Long id) {
        log.info(">> UserController.getEmployerById enter");
        EmployerDto result = this.userService.findEmployerById(id);
        if (result == null) {
            log.info("<< UserController.getEmployerById exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new OperationalMessage("Employer with id: " + id + " not found"));
        }
        log.info("<< UserController.getEmployerById exit");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
