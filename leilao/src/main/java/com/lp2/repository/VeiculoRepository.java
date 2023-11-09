package com.lp2.repository;

import com.lp2.model.DispositivoInformatica;
import com.lp2.model.Veiculo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface VeiculoRepository<T extends Veiculo> extends JpaRepository<T, Long> {

    Optional<Veiculo> findByIdAndLeilaoId(Long idVeiculo, Long idLeilao);

}
