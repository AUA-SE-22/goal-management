package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.services.GoalService;
import com.datauser.goalmanagement.utils.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(GoalController.class)
class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoalService mockGoalService;

    //@Test
    void testFindById() throws Exception {
        // Setup
        // Configure GoalService.getGoalById(...).
        final GoalDto goalDto = new GoalDto(0L, "name", "detail", 0L, Date.valueOf(LocalDate.of(2020, 1, 1)),
                Status.ACCEPTED, "employerId", Date.valueOf(
                LocalDate.of(2020, 1, 1)), Date.valueOf(LocalDate.of(2020, 1, 1)));
        when(mockGoalService.getGoalById(0L)).thenReturn(goalDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/goals/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
