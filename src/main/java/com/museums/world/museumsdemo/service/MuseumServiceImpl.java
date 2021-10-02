package com.museums.world.museumsdemo.service;

import com.museums.world.museumsdemo.domain.MuseumDto;
import com.museums.world.museumsdemo.exception.ResourceNotFoundException;
import com.museums.world.museumsdemo.mapper.MuseumMapper;
import com.museums.world.museumsdemo.mapper.WorkMapper;
import com.museums.world.museumsdemo.model.Museum;
import com.museums.world.museumsdemo.model.Work;
import com.museums.world.museumsdemo.repository.MuseumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService {

    private final MuseumRepository museumRepository;
    private final MuseumMapper museumMapper;
    private final WorkMapper workMapper;

    @Override
    public Museum findMuseumWithWorks(Integer id) {
        MuseumDto museumDto = museumRepository.findMuseumWithWorksById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No museum found for id [" + id + "]."));
        Museum museum = museumMapper.dtoToModel(museumDto);

        List<Work> works = museumDto.getWorks().stream()
                .map(workMapper::dtoToModel)
                .collect(Collectors.toList());
        museum.setWorks(works);

        return museum;
    }

    @Override
    public List<Museum> findAllMuseumsWithWorks() {
        List<MuseumDto> museumsDto = museumRepository.findAllMuseumsWithWorks()
                .orElse(Collections.emptyList());

        List<Museum> museums = museumsDto.stream()
                .map(museumMapper::dtoToModel)
                .collect(Collectors.toList());

        museums.forEach(museum -> {
            MuseumDto foundMuseumDto = museumsDto.stream().filter(museumDto -> museumDto.getId().equals(museum.getId())).findFirst().orElse(new MuseumDto());
            List<Work> works = foundMuseumDto.getWorks().stream().map(workMapper::dtoToModel).collect(Collectors.toList());
            museum.setWorks(works);
        });

        return museums;
    }

    @Override
    public Museum saveMuseum(Museum museum) {
        MuseumDto savedMuseum = museumRepository.save(museumMapper.modelToDto(museum));

        return museumMapper.dtoToModel(savedMuseum);
    }

    @Override
    public Museum updateMuseum(Integer id, Museum museum) {
        museumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No museum found for id [" + id + "]."));

        museum.setId(id);
        MuseumDto updatedMuseum = museumRepository.save(museumMapper.modelToDto(museum));

        return museumMapper.dtoToModel(updatedMuseum);
    }

    @Override
    public void deleteMuseum(Integer id) {
        museumRepository.deleteById(id);
    }
}
