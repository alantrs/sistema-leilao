package com.lp2.service;

import com.lp2.exception.CustomException;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.enums.StatusLeilao;
import com.lp2.model.leilao.Leilao;
import com.lp2.model.veiculo.DadosAtualizacaoVeiculoDTO;
import com.lp2.model.veiculo.Veiculo;
import com.lp2.model.veiculo.DadosEntradaVeiculoDTO;
import com.lp2.model.veiculo.DadosExibicaoVeiculoDTO;
import com.lp2.repository.LeilaoRepository;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class VeiculoService {

    @Inject
    private VeiculoRepository veiculoRepository;
    @Inject
    private LeilaoRepository leilaoRepository;

    public DadosExibicaoVeiculoDTO registrarVeiculo(DadosEntradaVeiculoDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Veiculo veiculo = new Veiculo(cadastro);
        veiculo.setLeilao(leilao.get());
        veiculoRepository.save(veiculo);
        return new DadosExibicaoVeiculoDTO(veiculo);
    }

    public List<DadosExibicaoVeiculoDTO> listarVeiculos(){
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream().map(veiculo -> new DadosExibicaoVeiculoDTO(veiculo)).toList();
    }

    public DadosExibicaoVeiculoDTO atualizarVeiculo(Long idVeiculo, DadosAtualizacaoVeiculoDTO atualizacao){
        Optional<Veiculo> veiculoEncontrado = veiculoRepository.findById(idVeiculo);
        Veiculo veiculo = new Veiculo(veiculoEncontrado.get(), atualizacao);
        veiculoRepository.update(veiculo);
        return new DadosExibicaoVeiculoDTO(veiculo);
    }

    public void deletarVeiculo(Long idVeiculo){
        veiculoRepository.deleteById(idVeiculo);
    }

    public void manipularVeiculoLeilao(Long idVeiculo, Long idLeilao){
        Optional<Veiculo> veiculo = veiculoRepository.findById(idVeiculo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        if (veiculo.get().getLances().isEmpty()){
            veiculo.get().setLeilao(leilaoEncontrado.get());
            veiculoRepository.update(veiculo.get());
        }else {
            throw new CustomException("Ação não permitida. Esse veiculo ja recebeu lance");
        }

    }
}
