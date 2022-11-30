package com.datauser.goalmanagement.repository.goals;

import com.datauser.goalmanagement.model.goals.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByKeycloakId(String keycloakId);

    Optional<Employee> findById(Long id);
}
