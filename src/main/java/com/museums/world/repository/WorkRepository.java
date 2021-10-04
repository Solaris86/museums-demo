package com.museums.world.repository;

import com.museums.world.domain.WorkDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<WorkDto, Integer> {
}
