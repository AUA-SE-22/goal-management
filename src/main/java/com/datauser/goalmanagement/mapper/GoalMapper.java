package com.datauser.goalmanagement.mapper;

import com.datauser.goalmanagement.dto.GoalDto;
import com.datauser.goalmanagement.model.goals.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GoalMapper {
    GoalMapper INSTANCE = Mappers.getMapper(GoalMapper.class);

    GoalDto goalToGoalDto(Goal goal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "employer", ignore = true)
    Goal goalDtoToGoal(GoalDto goalDto);

}
