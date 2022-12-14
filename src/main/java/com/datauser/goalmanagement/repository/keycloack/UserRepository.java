package com.datauser.goalmanagement.repository.keycloack;

import com.datauser.goalmanagement.model.keycloak.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id);
}
