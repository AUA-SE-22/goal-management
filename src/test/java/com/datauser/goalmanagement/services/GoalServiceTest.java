package com.datauser.goalmanagement.services;

import com.datauser.goalmanagement.controllers.UserController;
import com.datauser.goalmanagement.payload.request.EmployerGoalRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class GoalServiceTest {

    @Autowired
    GoalService goalService;

    @Test
    void validateEmployerGoalRequest() {
        Assert.notNull(goalService,"This should not be null");

        EmployerGoalRequest employerGoalRequest = new EmployerGoalRequest();
        employerGoalRequest.setStatus("ACCEPTED");
        String result = goalService.validateEmployerGoalRequest(employerGoalRequest);
        Assert.isTrue(result.equals(""),"Validations have failed");
    }
}