package com.lp2.service;

import com.lp2.dominio.Veiculo;
import com.lp2.dominio.dto.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.dto.veiculo.DadosExibicaoVeiculo;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class VeiculoService {

    @Inject
    private VeiculoRepository veiculoRepository;

    public DadosExibicaoVeiculo salvarVeiculo(DadosEntradaVeiculo cadastro){
        Veiculo veiculo = new Veiculo(cadastro);
        veiculoRepository.save(veiculo);
        return new DadosExibicaoVeiculo(veiculo);
    }
    
}
