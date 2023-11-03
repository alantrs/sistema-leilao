package com.lp2.repository;

import com.lp2.model.Lance;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {
}
