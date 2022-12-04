package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.dto.OperationalMessage;
import com.datauser.goalmanagement.model.goals.Employee;
import com.datauser.goalmanagement.payload.request.EmployeeGoalCreateRequest;
import com.datauser.goalmanagement.payload.request.EmployeeGoalUpdateRequest;
import com.datauser.goalmanagement.services.GoalService;
import com.datauser.goalmanagement.services.UserService;
import com.datauser.goalmanagement.services.UtilityService;
import com.datauser.goalmanagement.services.ValidationService;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee/goals")
public class EmployeeGoalController {

    private final UserService userService;

    private final GoalService goalService;
    private final HttpServletRequest httpServletRequest;

    private final UtilityService utilityService;

    private final ValidationService validationService;

    @Autowired
    public EmployeeGoalController(UserService userService,
                                  GoalService goalService,
                                  HttpServletRequest httpServletRequest,
                                  UtilityService utilityService, ValidationService validationService) {
        this.userService = userService;
        this.goalService = goalService;
        this.httpServletRequest = httpServletRequest;
        this.utilityService = utilityService;
        this.validationService = validationService;
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
            Employee employee =this.userService.findEmployeeByKeyCloakId(keyCloakUserId);

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


    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeGoalCreateRequest request) {
        log.info(">> EmployeeGoalsController.create enter");
        try {
            String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
            Employee employee =this.userService.findEmployeeByKeyCloakId(keyCloakUserId);
            var employersList = userService.getAllEmployer();
            if (employersList.isEmpty()) {
                log.error("<< EmployeeGoalsController.create exit FAIL: No Employer found");
            }
            Page<GoalDto> goalDtosPage = this.goalService.getAllEmployeeGoals(employee.getId(), PageRequest.of(0, Integer.MAX_VALUE));
            List<GoalDto> goalDtos = goalDtosPage.getContent();
            if (goalDtos.size() > 10) {
                log.error("<< EmployeeGoalsController.create exit FAIL: `{}` employee has more than 10 goal", employee.getFirstName() + " " + employee.getLastName());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage("Not more than 11 goals is allowed"));
            }
            String validationKey = goalService.validateEmployeeGoalRequest(request);
            if (!validationKey.equals("")) {
                log.error("<< EmployeeGoalsController.create exit FAIL: {}", validationKey);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(validationKey));
            }
            GoalDto goalDto = this.goalService.createEmployeeGoal(request, employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(goalDto);
        } catch (Exception e) {
            log.error("<< EmployeeGoalsController.create exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody EmployeeGoalUpdateRequest request) {
        log.info(">> EmployeeGoalsController.update enter");
        try {
            String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
            Employee employee =this.userService.findEmployeeByKeyCloakId(keyCloakUserId);
            GoalDto goalDto = this.goalService.updateEmployeeGoal(id, request);
            log.error(goalDto.toString());
            if (goalDto == null) {
                log.error("<< EmployeeGoalsController.create exit FAIL");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new OperationalMessage("Goal with id: " + id + " not found"));
            }
            log.info("<< EmployeeGoalsController.update exit");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(goalDto);
        } catch (Exception e) {
            log.error("<< EmployeeGoalsController.update exit FAIL");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }

    }
}
