package com.datauser.goalmanagement.mapper;

import com.datauser.goalmanagement.dto.EmployerDto;
import com.datauser.goalmanagement.model.goals.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployerMapper {
    EmployerMapper INSTANCE = Mappers.getMapper(EmployerMapper.class);

    @Mapping(target = "role", ignore = true)
    EmployerDto employerToEmployerDto(Employer employer);

    @Mapping(target = "goals", ignore = true)
    @Mapping(target = "keycloakId", ignore = true)
    Employer employerDtoToEmployer(EmployerDto employerDto);
}
