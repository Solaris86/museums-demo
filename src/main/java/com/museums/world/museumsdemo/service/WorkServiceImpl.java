package com.museums.world.museumsdemo.service;

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

    @Override
    public Work findWork(Integer id) {
        return null;
    }

    @Override
    public Work saveWork(Work work) {
        return null;
    }

    @Override
    public Work updateWork(Work work) {
        return null;
    }

    @Override
    public void deleteWork(Work work) {

    }
}
