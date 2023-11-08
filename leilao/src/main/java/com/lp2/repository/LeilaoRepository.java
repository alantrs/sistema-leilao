package com.lp2.repository;

import com.lp2.model.Leilao;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {
    List<Leilao> findAllOrderByDataOcorrencia();
}
