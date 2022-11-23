package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.utils.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("employer/goals")
public class EmployerGoalsController {

    private static boolean isDatabaseUpdated = false;

    private final HttpServletRequest httpServletRequest;

    private final UtilityService utilityService;

    @Autowired
    public EmployerGoalsController(HttpServletRequest httpServletRequest, UtilityService utilityService) {
        this.httpServletRequest = httpServletRequest;
        this.utilityService = utilityService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {

        String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(">> Employer goals");
    }
}
