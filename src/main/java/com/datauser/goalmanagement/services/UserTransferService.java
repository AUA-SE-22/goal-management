package com.datauser.goalmanagement.services;


import com.datauser.goalmanagement.model.Employee;
import com.datauser.goalmanagement.model.keycloak.User;
import com.datauser.goalmanagement.model.keycloak.UserAttribute;
import com.datauser.goalmanagement.repository.EmployeeRepository;
import com.datauser.goalmanagement.repository.EmployerRepository;
import com.datauser.goalmanagement.repository.keyckloack.UserAttributeRepository;
import com.datauser.goalmanagement.repository.keyckloack.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserTransferService {

    private static final String EMPLOYEE = "employee";
    private static final String EMPLOYER = "employer";

    private final UserRepository userRepository;

    private final UserAttributeRepository userAttributeRepository;

    private final EmployeeRepository employeeRepository;

    private final EmployerRepository employerRepository;



    @Autowired
    public UserTransferService(UserRepository userRepository,
                               UserAttributeRepository userAttributeRepository,
                               EmployeeRepository employeeRepository,
                               EmployerRepository employerRepository) {
        this.userRepository = userRepository;
        this.userAttributeRepository = userAttributeRepository;
        this.employeeRepository = employeeRepository;
        this.employerRepository = employerRepository;
    }

    public void transferUsersToEmployee() {
        log.info(">> UserTransferService.transferUsersToEmployee enter");
        List<UserAttribute> userAttributes = this.userAttributeRepository.findAllByName(EMPLOYEE);
        populateUsers(userAttributes);
        log.info(">> UserTransferService.transferUsersToEmployee exit");

    }

    public void transferUsersToEmployer() {
        log.info(">> UserTransferService.transferUsersToEmployer enter");
        List<UserAttribute> userAttributes = this.userAttributeRepository.findAllByName(EMPLOYER);
        populateUsers(userAttributes);
        log.info(">> UserTransferService.transferUsersToEmployer exit");

    }

    private void populateUsers(List<UserAttribute> userAttributes) {
        List<String> employeeUserIds = userAttributes.stream().map(UserAttribute::getUserId).toList();
        if (!employeeUserIds.isEmpty()) {
            employeeUserIds.forEach(userId -> {
                Optional<User> userOptional = this.userRepository.findById(userId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    Employee savedEmployee = this.employeeRepository.save(new Employee(userId, user.getFirstName(), user.getLastName(), user.getEmail()));
                }
            });
        }
    }
}
