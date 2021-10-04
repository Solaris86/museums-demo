package com.museums.world.mapper;

import com.museums.world.domain.MuseumDto;
import com.museums.world.model.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WorkMapper.class)
public interface MuseumMapper {

    MuseumDto modelToDto(Museum museum);

    @Mapping(target = "works", ignore = true)
    Museum dtoToModel(MuseumDto museumDto);

}
