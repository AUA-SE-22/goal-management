package com.datauser.goalmanagement.repository.goals;

import com.datauser.goalmanagement.model.goals.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    List<Employer> findAllByKeycloakId(String keycloakId);

}
