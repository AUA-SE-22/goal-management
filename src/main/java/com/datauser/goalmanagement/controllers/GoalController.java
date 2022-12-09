package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.dto.OperationalMessage;
import com.datauser.goalmanagement.services.GoalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("goals")
@CrossOrigin(origins = "*")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        log.info(">> GoalController.findById enter");
        GoalDto result = this.goalService.getGoalById(id);
        if (result == null) {
            log.info("<< GoalController.findById exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage("No goal with id: " + id));
        }
        log.info("<< GoalController.findById exit");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
