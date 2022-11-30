package com.datauser.goalmanagement.mapper;

import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.model.goals.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "role", ignore = true)
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "goals", ignore = true)
    @Mapping(target = "keycloakId", ignore = true)
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
