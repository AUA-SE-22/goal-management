package com.datauser.goalmanagement.repository.goals;

import com.datauser.goalmanagement.model.goals.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    List<Employer> findAllByKeycloakId(String keycloakId);

    Optional<Employer> findById(Long id);

}
