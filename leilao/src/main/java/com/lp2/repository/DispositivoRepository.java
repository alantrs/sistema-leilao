package com.lp2.repository;

import com.lp2.model.DispositivoInformatica;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DispositivoRepository<T extends DispositivoInformatica> extends JpaRepository<T, Long> {
    Optional<DispositivoInformatica> findByIdAndLeilaoId(Long idDispositivo, Long idLeilao);

    List<T> findByLeilaoIdAndValorInicialBetween(Long idLeilao, BigDecimal min, BigDecimal max);
}
