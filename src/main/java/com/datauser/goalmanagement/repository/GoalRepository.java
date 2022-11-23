package com.datauser.goalmanagement.repository;

import com.datauser.goalmanagement.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
