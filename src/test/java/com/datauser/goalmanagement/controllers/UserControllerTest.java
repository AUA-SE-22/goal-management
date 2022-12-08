package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.dto.EmployerDto;
import com.datauser.goalmanagement.services.UserService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    //@Test
    void testGetAllEmployee() throws Exception {
        // Setup
        // Configure UserService.getAllEmployee(...).
        final List<EmployeeDto> employeeDtos = List.of(new EmployeeDto(0L, "firstName", "lastName", "email", "role"));
        when(mockUserService.getAllEmployee()).thenReturn(employeeDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    //@Test
    void testGetAllEmployee_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAllEmployee()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    //@Test
    void testGetEmployeeById() throws Exception {
        // Setup
        // Configure UserService.findEmployeeById(...).
        final EmployeeDto employeeDto = new EmployeeDto(0L, "firstName", "lastName", "email", "role");
        when(mockUserService.findEmployeeById(0L)).thenReturn(employeeDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employees/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    //@Test
    void testGetAllEmployer() throws Exception {
        // Setup
        // Configure UserService.getAllEmployer(...).
        final List<EmployerDto> employerDtos = List.of(new EmployerDto(0L, "firstName", "lastName", "email", "role"));
        when(mockUserService.getAllEmployer()).thenReturn(employerDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    //@Test
    void testGetAllEmployer_UserServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUserService.getAllEmployer()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    //@Test
    void testGetEmployerById() throws Exception {
        // Setup
        // Configure UserService.findEmployerById(...).
        final EmployerDto employerDto = new EmployerDto(0L, "firstName", "lastName", "email", "role");
        when(mockUserService.findEmployerById(0L)).thenReturn(employerDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8081/users/employers/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
