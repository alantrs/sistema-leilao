package com.lp2.service;

import com.lp2.dominio.Veiculo;
import com.lp2.dominio.dto.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.dto.veiculo.DadosExibicaoVeiculo;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

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

    public DadosExibicaoVeiculo atualizarVeiculo(Long idVeiculo, DadosEntradaVeiculo atualizacao){
        Optional<Veiculo> veiculoEncontrado = veiculoRepository.findById(idVeiculo);

        Veiculo veiculo = new Veiculo(veiculoEncontrado.get(), atualizacao);
        veiculoRepository.update(veiculo);
        return new DadosExibicaoVeiculo(veiculo);
    }
}
