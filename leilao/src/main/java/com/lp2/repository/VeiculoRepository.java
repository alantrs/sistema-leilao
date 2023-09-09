package com.lp2.repository;

import com.lp2.dominio.veiculo.Veiculo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
