package com.lp2.repository;

import com.lp2.dominio.Veiculo;
import io.micronaut.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}