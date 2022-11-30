package com.datauser.goalmanagement.dto;

import com.datauser.goalmanagement.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalDto {
    private Long id;

    private String name;

    private String detail;

    private Long employeeId;

    private Date creationDate;

    private Status status;

    private String employerId;

    private Date approveDate;

    private Date rejectDate;
}
