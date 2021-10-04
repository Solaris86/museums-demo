package com.museums.world.repository;

import com.museums.world.domain.MuseumDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MuseumRepository extends JpaRepository<MuseumDto, Integer> {

    @Query("SELECT DISTINCT m FROM museum m LEFT JOIN FETCH m.works w WHERE m.id = :museumId")
    Optional<MuseumDto> findMuseumWithWorksById(Integer museumId);

    @Query("SELECT DISTINCT m FROM museum m LEFT JOIN FETCH m.works w")
    Optional<List<MuseumDto>> findAllMuseumsWithWorks();
}
