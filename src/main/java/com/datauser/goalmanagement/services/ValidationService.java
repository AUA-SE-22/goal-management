package com.datauser.goalmanagement.services;

import com.datauser.goalmanagement.utils.Status;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ValidationService {

    private static final String GOAL_NAME_MANDATORY = "Please file the target name of goal";
    private static final String GOAL_DETAIL_MANDATORY = "Please file the detail of goal";
    private static final String GOAL_EMPLOYEE_MANDATORY = "Please file the employeeId of goal";
    private static final String GOAL_STATUS_MANDATORY = "Please file the status of goal";

    private static final String INVALID_GOAL_STATUS = "Given status is invalid";


    private static final String GOAL_CREATION_DATE_MANDATORY = "Please file the creation date of goal";
    private static final String GOAL_EMPLOYER_MANDATORY = "Please file the employerId of goal";

    public String checkName(String name) {
        String errorKey = "";
        if (name == null || name.equals("")) {
            errorKey = GOAL_NAME_MANDATORY;
        }
        return errorKey;
    }


    public String checkDetail(String detail) {
        String errorKey = "";
        if (detail == null || detail.equals("")) {
            errorKey = GOAL_DETAIL_MANDATORY;
        }
        return errorKey;
    }


    public String checkEmployeeId(Long employeeId) {
        String errorKey = "";
        if (employeeId == null) {
            errorKey = GOAL_EMPLOYEE_MANDATORY;
        }
        return errorKey;
    }

    public String checkEmployerId(Long employerId) {
        String errorKey = "";
        if (employerId == null) {
            errorKey = GOAL_EMPLOYER_MANDATORY;
        }
        return errorKey;
    }


    public String checkStatus(String status) {
        String errorKey = "";
        if (status == null || status.equals("")) {
            errorKey = GOAL_STATUS_MANDATORY;
        }
        return errorKey;
    }

    public String checkStatusValidity(String status) {
        String errorKey = "";
        if (!isValidStatus(status)) {
            errorKey = INVALID_GOAL_STATUS;
        }
        return errorKey;
    }



    public String checkCreationDate(Date creationDate) {
        String errorKey = "";
        if (creationDate == null) {
            errorKey = GOAL_CREATION_DATE_MANDATORY;
        }
        return errorKey;
    }
    private boolean isValidStatus(String statusString) {
        for (Status status: Status.values()) {
            if (status.name().equals(statusString)) {
                return true;
            }
        }
        return false;
    }
}
