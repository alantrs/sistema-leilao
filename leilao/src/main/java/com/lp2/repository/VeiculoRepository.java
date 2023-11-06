package com.lp2.repository;

import com.lp2.model.Veiculo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface VeiculoRepository<T extends Veiculo> extends JpaRepository<T, Long> {
}
