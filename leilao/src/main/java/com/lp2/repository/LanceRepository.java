package com.lp2.repository;

import com.lp2.model.DispositivoInformatica;
import com.lp2.model.Lance;
import com.lp2.model.Veiculo;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {
    List<Lance> findAllByDispositivoInformaticaId(Long idProduto);

    List<Lance> findAllByVeiculoId(Long idProduto);

    Optional<Lance> findTopByDispositivoInformaticaOrderByValorDesc(DispositivoInformatica dispositivo);

    Optional<Lance> findTopByVeiculoOrderByValorDesc(Veiculo veiculo);
}
