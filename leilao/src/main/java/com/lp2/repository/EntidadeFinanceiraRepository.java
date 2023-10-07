package com.lp2.repository;

import com.lp2.model.entidadeFinanceira.EntidadeFinanceira;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EntidadeFinanceiraRepository extends JpaRepository<EntidadeFinanceira, Long> {

    List<EntidadeFinanceira> findByIdIn(List<Long> ids);
}
