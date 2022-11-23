package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.services.UserTransferService;
import com.datauser.goalmanagement.utils.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("employee/goals")
public class EmployeeGoalsController {

    private final UserTransferService userTransferService;
    private final HttpServletRequest httpServletRequest;

    private final UtilityService utilityService;

    @Autowired
    public EmployeeGoalsController(UserTransferService userTransferService, HttpServletRequest httpServletRequest, UtilityService utilityService) {
        this.userTransferService = userTransferService;
        this.httpServletRequest = httpServletRequest;
        this.utilityService = utilityService;
        this.userTransferService.transferUsersToEmployee();

    }

    @GetMapping
    public ResponseEntity<?> getAll() {

        String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body("<<  Employee goals");
    }


}
