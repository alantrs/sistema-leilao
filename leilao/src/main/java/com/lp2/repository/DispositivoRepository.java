package com.lp2.repository;

import com.lp2.dominio.dispositivo.DispositivoInformatica;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoInformatica, Long> {
}
