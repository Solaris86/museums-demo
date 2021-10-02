package com.museums.world.museumsdemo.mapper;

import com.museums.world.museumsdemo.domain.MuseumDto;
import com.museums.world.museumsdemo.model.Museum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = WorkMapper.class)
public interface MuseumMapper {

    MuseumDto modelToDto(Museum museum);

    @Mapping(target = "works", ignore = true)
    Museum dtoToModel(MuseumDto museumDto);

}
