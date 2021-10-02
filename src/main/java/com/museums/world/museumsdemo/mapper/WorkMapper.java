package com.museums.world.museumsdemo.mapper;

import com.museums.world.museumsdemo.domain.WorkDto;
import com.museums.world.museumsdemo.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    WorkDto modelToDto(Work work);

    @Mapping(target = "museum", ignore = true)
    Work dtoToModel(WorkDto workDto);

}
