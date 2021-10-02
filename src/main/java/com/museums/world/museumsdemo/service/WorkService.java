package com.museums.world.museumsdemo.service;

import com.museums.world.museumsdemo.model.Work;

public interface WorkService {

    Work findWork(Integer id);

    Work saveWork(Work work);

    Work updateWork(Work work);

    void deleteWork(Work work);
}
