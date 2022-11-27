package com.datauser.goalmanagement.services;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.mapper.GoalMapper;
import com.datauser.goalmanagement.model.goals.Goal;
import com.datauser.goalmanagement.repository.goals.EmployeeRepository;
import com.datauser.goalmanagement.repository.goals.EmployerRepository;
import com.datauser.goalmanagement.repository.goals.GoalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public String validate(GoalDto goalDto) {
        String errorKey = this.validationService.checkName(goalDto.getName());
        if (!errorKey.equals("")) {
            return errorKey;
        }
//        errorKey = this.validationService.
        return "";
    }
}
