package com.lp2.service;

import com.lp2.model.veiculo.Veiculo;
import com.lp2.model.veiculo.DadosEntradaVeiculoDTO;
import com.lp2.model.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class VeiculoService {

    @Inject
    private VeiculoRepository veiculoRepository;

    public DadosExibicaoVeiculoDTO salvarVeiculo(DadosEntradaVeiculoDTO cadastro){
        Veiculo veiculo = new Veiculo(cadastro);
        veiculoRepository.save(veiculo);
        return new DadosExibicaoVeiculoDTO(veiculo);
    }

    public List<DadosExibicaoVeiculoDTO> listarVeiculos(){
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo)).toList();
    }

    public DadosExibicaoVeiculoDTO atualizarVeiculo(Long idVeiculo, DadosEntradaVeiculoDTO atualizacao){
        Optional<Veiculo> veiculoEncontrado = veiculoRepository.findById(idVeiculo);

        Veiculo veiculo = new Veiculo(veiculoEncontrado.get(), atualizacao);
        veiculoRepository.update(veiculo);
        return new DadosExibicaoVeiculoDTO(veiculo);
    }

    public void deletarVeiculo(Long idVeiculo){
        veiculoRepository.deleteById(idVeiculo);
    }
}
