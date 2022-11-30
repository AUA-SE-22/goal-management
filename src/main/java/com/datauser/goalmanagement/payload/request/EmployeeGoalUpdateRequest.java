package com.datauser.goalmanagement.payload.request;

import lombok.Data;

@Data
public class EmployeeGoalUpdateRequest {
    private String name;
    private String detail;
}
