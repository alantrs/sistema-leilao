package com.lp2.repository;

import com.lp2.model.DispositivoInformatica;
import com.lp2.model.Veiculo;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.http.annotation.QueryValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository<T extends Veiculo> extends JpaRepository<T, Long> {

    Optional<Veiculo> findByIdAndLeilaoId(Long idVeiculo, Long idLeilao);

    List<T> findByLeilaoIdAndValorInicialBetween(Long idLeilao, BigDecimal min, BigDecimal max);

    List<Veiculo> findAllByLeilaoIdAndModeloContaining(Long idLeilao, String nome);

    List<Veiculo> findAllByLeilaoId(Long idLeilao);
}
