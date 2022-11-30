package com.datauser.goalmanagement.services;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.mapper.GoalMapper;
import com.datauser.goalmanagement.model.goals.Employee;
import com.datauser.goalmanagement.model.goals.Employer;
import com.datauser.goalmanagement.model.goals.Goal;
import com.datauser.goalmanagement.payload.request.EmployeeGoalCreateRequest;
import com.datauser.goalmanagement.payload.request.EmployeeGoalUpdateRequest;
import com.datauser.goalmanagement.payload.request.EmployerGoalRequest;
import com.datauser.goalmanagement.repository.goals.EmployeeRepository;
import com.datauser.goalmanagement.repository.goals.EmployerRepository;
import com.datauser.goalmanagement.repository.goals.GoalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.datauser.goalmanagement.utils.Status.*;

@Slf4j
@Service
public class GoalService {
    private final EmployeeRepository employeeRepository;

    private final EmployerRepository employerRepository;

    private final GoalRepository goalRepository;

    private final ValidationService validationService;

    @Autowired
    public GoalService(EmployeeRepository employeeRepository,
                       EmployerRepository employerRepository,
                       GoalRepository goalRepository, ValidationService validationService) {
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
        this.goalRepository = goalRepository;
        this.validationService = validationService;
    }

    public Page<GoalDto> getAllEmployeeGoals(Long employeeId, Pageable pageable) {
        log.info(">> GoalService.getAllEmployeeGoals enter");
        Page<Goal> goalPage = this.goalRepository.findAllByEmployeeId(employeeId, pageable);
        List<Goal> goals = goalPage.getContent();
        List<GoalDto> goalDtos = goals.stream().map(GoalMapper.INSTANCE::goalToGoalDto).collect(Collectors.toList());
        log.info("<< GoalService.getAllEmployeeGoals exit");
        return new PageImpl<>(goalDtos, pageable, goalPage.getTotalElements());
    }

    public Page<GoalDto> getAllEmployeeGoalsByName(Long employeeId, String name, Pageable pageable) {
        log.info(">> GoalService.getAllEmployeeGoalsByName enter");
        Page<Goal> goalPage = this.goalRepository.findAllByEmployeeIdAndNameContaining(employeeId, name, pageable);
        List<Goal> goals = goalPage.getContent();
        List<GoalDto> goalDtos = goals.stream().map(GoalMapper.INSTANCE::goalToGoalDto).collect(Collectors.toList());
        log.info("<< GoalService.getAllEmployeeGoalsByName exit");
        return new PageImpl<>(goalDtos, pageable, goalPage.getTotalElements());
    }

    public Page<GoalDto> getAllEmployerGoals(Long employerId, Pageable pageable) {
        log.info(">> GoalService.getAllEmployerGoals enter");
        Page<Goal> goalPage = this.goalRepository.findAllByEmployerId(employerId, pageable);
        List<Goal> goals = goalPage.getContent();
        List<GoalDto> goalDtos = goals.stream().map(GoalMapper.INSTANCE::goalToGoalDto).collect(Collectors.toList());
        log.info("<< GoalService.getAllEmployerGoals exit");
        return new PageImpl<>(goalDtos, pageable, goalPage.getTotalElements());
    }

    public Page<GoalDto> getAllEmployerGoalsByName(Long employeeId, String name, Pageable pageable) {
        log.info(">> GoalService.getAllEmployerGoalsByName enter");
        Page<Goal> goalPage = this.goalRepository.findAllByEmployerIdAndNameContaining(employeeId, name, pageable);
        List<Goal> goals = goalPage.getContent();
        List<GoalDto> goalDtos = goals.stream().map(GoalMapper.INSTANCE::goalToGoalDto).collect(Collectors.toList());
        log.info("<< GoalService.getAllEmployerGoalsByName exit");
        return new PageImpl<>(goalDtos, pageable, goalPage.getTotalElements());
    }

    public GoalDto getGoalById(Long id) {
        log.info(">> GoalService.getEmployeeGoalById enter");
        Optional<Goal> optionalGoal = this.goalRepository.findById(id);
        if (optionalGoal.isEmpty()) {
            log.info("<< GoalService.getEmployeeGoalById exit FAIL: Goal with `{}` id not found", id);
            return null;
        }
        log.info("<< GoalService.getEmployeeGoalById exit");
        return GoalMapper.INSTANCE.goalToGoalDto(optionalGoal.get());
    }
    public GoalDto createEmployeeGoal(EmployeeGoalCreateRequest request, Employee employee) {
        log.info(">> GoalService.createEmployeeGoal enter");
        Goal goal = new Goal();
        goal.setName(request.getName());
        goal.setDetail(request.getDetail());
        goal.setEmployeeId(employee.getId());
        goal.setCreationDate(Date.valueOf(LocalDate.now()));
        goal.setEmployerId(request.getEmployerId());
        goal.setStatus(PENDING);
        Goal savedGoal = this.goalRepository.saveAndFlush(goal);
        log.info("<< GoalService.createEmployeeGoal exit");
        return GoalMapper.INSTANCE.goalToGoalDto(savedGoal);
    }

    public GoalDto updateEmployeeGoal(Long id, EmployeeGoalUpdateRequest request) {
        log.info(">> GoalService.updateEmployeeGoal enter");
        GoalDto goalDto = this.getGoalById(id);
        if (goalDto == null) {
            log.info("<< GoalService.updateEmployeeGoal exit FAIL:");
            return null;
        }
        Goal goal = GoalMapper.INSTANCE.goalDtoToGoal(goalDto);
        goal.setName(request.getName());
        goal.setDetail(request.getDetail());
        Goal savedGoal = this.goalRepository.saveAndFlush(goal);
        log.info("<< GoalService.updateEmployeeGoal exit");
        return GoalMapper.INSTANCE.goalToGoalDto(savedGoal);
    }

    public GoalDto updateAssignedGoalStatus(Long id, EmployerGoalRequest request) {
        log.info(">> GoalService.updateAssignedGoalStatus enter");
        GoalDto goalDto = this.getGoalById(id);
        if (goalDto == null) {
            log.info("<< GoalService.updateAssignedGoalStatus exit FAIL");
            return null;
        }
        Goal goal = GoalMapper.INSTANCE.goalDtoToGoal(goalDto);
        if (request.getStatus().equals(ACCEPTED.name())) {
            goal.setStatus(ACCEPTED);
            goal.setApproveDate(Date.valueOf(LocalDate.now()));
        } else if (request.getStatus().equals(REJECTED.name())) {
            goal.setStatus(REJECTED);
            goal.setRejectDate(Date.valueOf(LocalDate.now()));
        }
        Goal updatedGoal = this.goalRepository.save(goal);
        log.info("<< GoalService.updateAssignedGoalStatus exit");
        return GoalMapper.INSTANCE.goalToGoalDto(updatedGoal);
    }


    public String validateEmployeeGoalRequest(EmployeeGoalCreateRequest request) {
        if (request == null) {
            return "EMPTY REQUEST";
        }
        String errorKey = this.validationService.checkName(request.getName());
        if (!errorKey.equals("")) {
            return errorKey;
        }
        errorKey = this.validationService.checkDetail(request.getDetail());
        if (!errorKey.equals("")) {
            return errorKey;
        }
        errorKey = this.validationService.checkEmployerId(request.getEmployerId());
        if (!errorKey.equals("")) {
            return errorKey;
        }
        Long employerId = request.getEmployerId();
        Optional<Employer> employerOptional = this.employerRepository.findById(employerId);
        if (employerOptional.isEmpty()) {
            return String.format("Employer with `%d` not found", employerId);
        }
        return "";
    }



    public String validateEmployerGoalRequest(EmployerGoalRequest request) {
        if (request == null) {
            return "EMPTY REQUEST";
        }
        String errorKey = this.validationService.checkStatus(request.getStatus());
        if (!errorKey.equals("")) {
            return errorKey;
        }

        errorKey = this.validationService.checkStatusValidity(request.getStatus());
        if (!errorKey.equals("")) {
            return errorKey;
        }
        return "";
    }
}
