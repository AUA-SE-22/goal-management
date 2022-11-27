package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.dto.OperationalMessage;
import com.datauser.goalmanagement.model.goals.Employee;
import com.datauser.goalmanagement.services.GoalService;
import com.datauser.goalmanagement.services.UserService;
import com.datauser.goalmanagement.services.UtilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("employee/goals")
public class EmployeeGoalController {

    private final UserService userService;

    private final GoalService goalService;
    private final HttpServletRequest httpServletRequest;

    private final UtilityService utilityService;

    @Autowired
    public EmployeeGoalController(UserService userService,
                                  GoalService goalService,
                                  HttpServletRequest httpServletRequest,
                                  UtilityService utilityService) {
        this.userService = userService;
        this.goalService = goalService;
        this.httpServletRequest = httpServletRequest;
        this.utilityService = utilityService;


    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "-1") int page,
                                    @RequestParam(defaultValue = "-1") int size,
                                    @RequestParam(defaultValue = "name") String sortBy,
                                    @RequestParam(defaultValue = "asc") String sortDirection,
                                    @RequestParam(defaultValue = "") String name) {
        log.info(">> EmployeeGoalsController.getAll enter");
        try {
            String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
            Employee employee =this.userService.findEmployeeById(keyCloakUserId);

            if (page < 0) {
                page = 0;
            }
            if (size < 0) {
                size = Integer.MAX_VALUE;
            }
            Sort sort = sortDirection.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<GoalDto> response;
            if (name.equals("")) {
                response = this.goalService.getAllEmployeeGoals(employee.getId(), pageable);
            } else {
                response = this.goalService.getAllEmployeeGoalsByName(employee.getId(), name, pageable);
            }
            log.info("<< EmployeeGoalsController.getAll exit");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("<< EmployeeGoalsController.getAll exit FAIL: {}", String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }
    }

    public ResponseEntity<?> create(@RequestBody GoalDto goalDto) {
        log.info(">> EmployeeGoalsController.create enter");
        try {
            String validationKey = goalService.validate(goalDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            log.error(">> EmployeeGoalsController.create enter");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }
    }


}
