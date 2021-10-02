package com.museums.world.museumsdemo.repository;

import com.museums.world.museumsdemo.domain.WorkDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<WorkDto, Integer> {
}
