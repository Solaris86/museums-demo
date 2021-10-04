package com.museums.world.service;

import com.museums.world.model.Museum;

import java.util.List;

public interface MuseumService {

    Museum findMuseumWithWorks(Integer id);

    List<Museum> findAllMuseumsWithWorks();

    Museum saveMuseum(Museum museum);

    Museum updateMuseum(Integer id, Museum museum);

    void deleteMuseum(Integer id);
}
