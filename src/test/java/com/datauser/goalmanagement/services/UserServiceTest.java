

package com.datauser.goalmanagement.services;
import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.model.goals.Employee;
import com.datauser.goalmanagement.repository.goals.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {



    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private UserService employeeService;

    private Employee employee;

    public void setup(){
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Arame")
                .lastName("Arakelyan")
                .email("arakelyan@gmail.com")
                .build();
    }

    // JUnit test for getAllEmployee method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Tigran")
                .lastName("Petrosyan")
                .email("petrosyan@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // when -  action or the behaviour that we are going test
        List<EmployeeDto> employeeList = employeeService.getAllEmployee();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for getAllEmployee method
    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Tony")
                .lastName("Stark")
                .email("tony@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<EmployeeDto> employeeList = employeeService.getAllEmployee();

        // then - verify the output
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }


    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // when
        Employee savedEmployee  = employeeService.findEmployeeById(employee.getId()).get();

        // then
        assertThat(savedEmployee).isNotNull();

    }
}

