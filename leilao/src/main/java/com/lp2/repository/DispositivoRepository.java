package com.lp2.repository;

import com.lp2.model.DispositivoInformatica;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DispositivoRepository<T extends DispositivoInformatica> extends JpaRepository<T, Long> {
}
