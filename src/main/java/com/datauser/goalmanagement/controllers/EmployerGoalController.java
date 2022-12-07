package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.dto.OperationalMessage;
import com.datauser.goalmanagement.model.goals.Employer;
import com.datauser.goalmanagement.payload.request.EmployerGoalRequest;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("employer/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployerGoalController {

    private final UserService userService;

    private final GoalService goalService;
    private final HttpServletRequest httpServletRequest;

    private final UtilityService utilityService;

    @Autowired
    public EmployerGoalController(UserService userService,
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
        log.info(">> EmployerGoalsController.getAll enter");
        try {
            String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
            Employer employer =this.userService.findEmployerByKeyCloakId(keyCloakUserId);

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
                response = this.goalService.getAllEmployerGoals(employer.getId(), pageable);
            } else {
                response = this.goalService.getAllEmployerGoalsByName(employer.getId(), name, pageable);
            }
            log.info("<< EmployerGoalsController.getAll exit");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("<< EmployerGoalsController.getAll exit FAIL: {}", String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssignedGoal(@PathVariable("id") Long id,
                                      @RequestBody EmployerGoalRequest request) {
        log.info(">> EmployerGoalsController.updateAssignedGoal enter");
        try {
            String keyCloakUserId = this.utilityService.getUserData(this.httpServletRequest);
            Employer employer =this.userService.findEmployerByKeyCloakId(keyCloakUserId);
            Page<GoalDto> goalDtosPage = this.goalService.getAllEmployerGoals(employer.getId(),
                    PageRequest.of(0, Integer.MAX_VALUE));
            List<GoalDto> goalDtos = goalDtosPage.getContent();
            if (goalDtos.size() > 20) {
                log.error("<< EmployerGoalsController.updateAssignedGoal exit FAIL: `{}` employee has more than 20 goal", employer.getFirstName() + " " + employer.getLastName());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage("Not more than 21 goals is allowed"));
            }
            String validationKey = goalService.validateEmployerGoalRequest(request);
            if (!validationKey.equals("")) {
                log.error("<< EmployerGoalsController.updateAssignedGoal exit FAIL: {}", validationKey);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(validationKey));
            }
            GoalDto goalDto = this.goalService.updateAssignedGoalStatus(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(goalDto);
        } catch (Exception e) {
            log.error("<< EmployerGoalsController.updateAssignedGoal exit");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OperationalMessage(e.getMessage()));
        }
    }

}
