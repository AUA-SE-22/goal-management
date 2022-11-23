package com.datauser.goalmanagement.repository.keyckloack;

import com.datauser.goalmanagement.model.keycloak.UserAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttributeRepository extends JpaRepository<UserAttribute, String> {

    List<UserAttribute> findAllByName(String name);
}
