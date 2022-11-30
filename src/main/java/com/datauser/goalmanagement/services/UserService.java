package com.datauser.goalmanagement.services;


import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.dto.EmployerDto;
import com.datauser.goalmanagement.mapper.EmployeeMapper;
import com.datauser.goalmanagement.mapper.EmployerMapper;
import com.datauser.goalmanagement.model.goals.Employee;
import com.datauser.goalmanagement.model.goals.Employer;
import com.datauser.goalmanagement.model.keycloak.User;
import com.datauser.goalmanagement.model.keycloak.UserAttribute;
import com.datauser.goalmanagement.repository.goals.EmployeeRepository;
import com.datauser.goalmanagement.repository.goals.EmployerRepository;
import com.datauser.goalmanagement.repository.keycloack.UserAttributeRepository;
import com.datauser.goalmanagement.repository.keycloack.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private static final String EMPLOYEE = "employee";
    private static final String EMPLOYER = "employer";

    private final UserRepository userRepository;

    private final UserAttributeRepository userAttributeRepository;

    private final EmployeeRepository employeeRepository;

    private final EmployerRepository employerRepository;


    @Autowired
    public UserService(UserRepository userRepository,
                       UserAttributeRepository userAttributeRepository,
                       EmployeeRepository employeeRepository,
                       EmployerRepository employerRepository) {
        this.userRepository = userRepository;
        this.userAttributeRepository = userAttributeRepository;
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }

    public List<EmployeeDto> getAllEmployee() {
        log.info(">> UserService.getAllEmployee enter");
        transferUsersToEmployee();
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> {
            EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
            employeeDto.setRole("Employee");
            return employeeDto;
        }).toList();
        log.info("<< UserService.getAllEmployee exit");
        return employeeDtos;
    }

    public EmployeeDto findEmployeeById(Long id) {
        log.info(">> UserService.findEmployeeById enter");
        Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            log.info("<< UserService.findEmployeeById exit FAIL: Employee with `{}` id not found", id);
            return null;
        }
        Employee employee = employeeOptional.get();
        EmployeeDto employeeDto = EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee);
        employeeDto.setRole("Employee");
        log.info("<< UserService.findEmployeeById exit");
        return employeeDto;
    }



    public Employee findEmployeeByKeyCloakId(String keyCloakUserId) {
        log.info(">> UserService.findEmployeeByKeyCloakId enter");
        List<Employee> employees = this.employeeRepository.findAllByKeycloakId(keyCloakUserId);
        if (employees.isEmpty()) {
            Optional<User> userOptional = this.userRepository.findById(keyCloakUserId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                log.info("<< UserService.findEmployeeByKeyCloakId exit");
                return this.employeeRepository.save(new Employee(keyCloakUserId, user.getFirstName(), user.getLastName(), user.getEmail()));
            }
        }
        log.info("<< UserService.findEmployeeByKeyCloakId exit");
        return employees.get(0);
    }

    public List<EmployerDto> getAllEmployer() {
        log.info(">> UserService.getAllEmployer enter");
        transferUsersToEmployer();
        List<Employer> employers = this.employerRepository.findAll();
        List<EmployerDto> employerDtos = employers.stream().map(employer -> {
            EmployerDto employerDto = EmployerMapper.INSTANCE.employerToEmployerDto(employer);
            employerDto.setRole("Employer");
            return employerDto;
        }).toList();
        log.info("<< UserService.getAllEmployer exit");
        return employerDtos;
    }

    public EmployerDto findEmployerById(Long id) {
        log.info(">> UserService.findEmployerById enter");
        Optional<Employer> employerOptional = this.employerRepository.findById(id);
        if (employerOptional.isEmpty()) {
            log.info("<< UserService.findEmployerById exit FAIL: Employer with `{}` id not found", id);
            return null;
        }
        Employer employer = employerOptional.get();
        EmployerDto employerDto = EmployerMapper.INSTANCE.employerToEmployerDto(employer);
        employerDto.setRole("Employer");
        log.info("<< UserService.findEmployerById exit");
        return employerDto;
    }

    public Employer findEmployerByKeyCloakId(String keyCloakUserId) {
        log.info(">> UserService.findEmployerByKeyCloakId enter");
        List<Employer> employers = this.employerRepository.findAllByKeycloakId(keyCloakUserId);
        if (employers.isEmpty()) {
            Optional<User> userOptional = this.userRepository.findById(keyCloakUserId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                log.info("<< UserService.findEmployerByKeyCloakId exit");
                return this.employerRepository.save(new Employer(keyCloakUserId, user.getFirstName(), user.getLastName(), user.getEmail()));
            }
        }
        log.info("<< UserService.findEmployerByKeyCloakId exit");
        return employers.get(0);
    }

    private void transferUsersToEmployee() {
        log.info(">> UserTransferService.transferUsersToEmployee enter");
        List<UserAttribute> userAttributes = this.userAttributeRepository.findAllByValue(EMPLOYEE);
        populateUsersToEmployee(userAttributes);
        log.info(">> UserTransferService.transferUsersToEmployee exit");

    }

    private void transferUsersToEmployer() {
        log.info(">> UserTransferService.transferUsersToEmployer enter");
        List<UserAttribute> userAttributes = this.userAttributeRepository.findAllByValue(EMPLOYER);
        populateUsersToEmployer(userAttributes);
        log.info(">> UserTransferService.transferUsersToEmployer exit");

    }

    private void populateUsersToEmployer(List<UserAttribute> userAttributes) {
        if (userAttributes == null || userAttributes.isEmpty()) {
            return;
        }
        List<String> userKeyCloakIds = userAttributes.stream().map(UserAttribute::getUserId).toList();
        userKeyCloakIds.forEach(this::findEmployerByKeyCloakId);
    }

    private void populateUsersToEmployee(List<UserAttribute> userAttributes) {
        if (userAttributes == null || userAttributes.isEmpty()) {
            return;
        }
        List<String> userKeyCloakIds = userAttributes.stream().map(UserAttribute::getUserId).toList();
        userKeyCloakIds.forEach(this::findEmployeeByKeyCloakId);
    }


}
