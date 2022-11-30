package com.datauser.goalmanagement.payload.request;

import lombok.Data;

@Data
public class EmployeeGoalCreateRequest {
    private String name;
    private String detail;
    private Long employerId;
}
