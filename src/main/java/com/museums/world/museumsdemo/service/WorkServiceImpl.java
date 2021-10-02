package com.museums.world.museumsdemo.service;

import com.museums.world.museumsdemo.domain.MuseumDto;
import com.museums.world.museumsdemo.domain.WorkDto;
import com.museums.world.museumsdemo.exception.ResourceNotFoundException;
import com.museums.world.museumsdemo.mapper.WorkMapper;
import com.museums.world.museumsdemo.model.Work;
import com.museums.world.museumsdemo.repository.MuseumRepository;
import com.museums.world.museumsdemo.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final MuseumRepository museumRepository;
    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    @Override
    public Work findWork(Integer workId) {
        WorkDto workDto = workRepository.findById(workId)
                .orElseThrow(() -> new ResourceNotFoundException("No artwork found for id [" + workId + "]."));

        return workMapper.dtoToModel(workDto);
    }

    @Override
    public Work saveWork(Integer museumId, Work work) {
        MuseumDto museumDto = museumRepository.findById(museumId)
                .orElseThrow(() -> new ResourceNotFoundException("No museum found for id [" + museumId + "]."));

        WorkDto workDto = workMapper.modelToDto(work);
        workDto.setMuseum(museumDto);

        WorkDto savedWorkDto = workRepository.save(workDto);

        return workMapper.dtoToModel(savedWorkDto);
    }

    @Override
    public Work updateWork(Integer museumId, Integer workId, Work work) {
        MuseumDto museumDto = museumRepository.findById(museumId)
                .orElseThrow(() -> new ResourceNotFoundException("No museum found for id [" + museumId + "]."));

        museumDto.getWorks().stream()
                .filter(workDto -> workDto.getId().equals(workId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No artwork with id [" + workId + "] found for museum with id [" + museumId + "]."));

        work.setId(workId);
        WorkDto workDto = workMapper.modelToDto(work);
        workDto.setMuseum(museumDto);

        WorkDto savedWorkDto = workRepository.save(workDto);

        return workMapper.dtoToModel(savedWorkDto);
    }

    @Override
    public void deleteWork(Integer id) {
        workRepository.deleteById(id);
    }
}
