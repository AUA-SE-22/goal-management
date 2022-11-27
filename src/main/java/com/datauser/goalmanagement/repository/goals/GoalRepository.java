package com.datauser.goalmanagement.repository.goals;

import com.datauser.goalmanagement.model.goals.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    Page<Goal> findAllByEmployeeId(Long employeeId, Pageable pageable);
    Page<Goal> findAllByEmployeeIdAndNameContaining(Long employeeId, String name, Pageable pageable);
}
