package com.lp2.service;

import com.lp2.dominio.Veiculo;
import com.lp2.dominio.dto.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.dto.veiculo.DadosExibicaoVeiculo;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class VeiculoService {

    @Inject
    private VeiculoRepository veiculoRepository;

    public DadosExibicaoVeiculo salvarVeiculo(DadosEntradaVeiculo cadastro){
        Veiculo veiculo = new Veiculo(cadastro);
        veiculoRepository.save(veiculo);
        return new DadosExibicaoVeiculo(veiculo);
    }

    public List<DadosExibicaoVeiculo> listarVeiculo(){
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().map(veiculo -> new DadosExibicaoVeiculo(veiculo)).toList();
    }
}
