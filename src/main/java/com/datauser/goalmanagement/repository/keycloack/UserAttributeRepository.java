package com.datauser.goalmanagement.repository.keycloack;

import com.datauser.goalmanagement.model.keycloak.UserAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttributeRepository extends JpaRepository<UserAttribute, String> {

    List<UserAttribute> findAllByValue(String name);
}
