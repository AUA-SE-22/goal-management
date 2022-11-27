package com.datauser.goalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String keycloakId;

    private String firstName;

    private String lastName;

    private String email;

    private String role;
}
