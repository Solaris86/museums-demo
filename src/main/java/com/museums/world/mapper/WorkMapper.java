package com.museums.world.mapper;

import com.museums.world.domain.WorkDto;
import com.museums.world.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    WorkDto modelToDto(Work work);

    @Mapping(target = "museum", ignore = true)
    Work dtoToModel(WorkDto workDto);

}
