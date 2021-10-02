package com.museums.world.museumsdemo.service;

import com.museums.world.museumsdemo.model.Work;

public interface WorkService {

    Work findWork(Integer workId);

    Work saveWork(Integer museumId, Work work);

    Work updateWork(Integer museumId, Integer workId, Work work);

    void deleteWork(Integer id);
}
