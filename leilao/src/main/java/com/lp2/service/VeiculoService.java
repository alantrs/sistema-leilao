package com.lp2.service;

import com.lp2.dto.dispositivo.DadosExibicaoSwitchDTO;
import com.lp2.dto.veiculo.*;
import com.lp2.exception.CustomException;
import com.lp2.model.*;
import com.lp2.repository.LeilaoRepository;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class VeiculoService {

    @Inject
    private VeiculoRepository<Veiculo> veiculoRepository;
    @Inject
    private VeiculoRepository<Carro> carroRepository;
    @Inject
    private VeiculoRepository<Caminhao> caminhaoRepository;
    @Inject
    private VeiculoRepository<Motocicleta> motocicletaRepository;
    @Inject
    private VeiculoRepository<Utilitario> utilitarioRepository;
    @Inject
    private LeilaoRepository leilaoRepository;
    ModelMapper modelMapper = new ModelMapper();

    public DadosExibicaoCarroDTO registrarVeiculoCarro(DadosEntradaCarroDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Carro carro = new Carro(cadastro);
        carro.setLeilao(leilao.get());
        carroRepository.save(carro);
        return modelMapper.map(carro, DadosExibicaoCarroDTO.class);
    }

    public DadosExibicaoCaminhaoDTO registrarVeiculoCaminhao(DadosEntradaCaminhaoDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Caminhao caminhao= new Caminhao(cadastro);
        caminhao.setLeilao(leilao.get());
        caminhaoRepository.save(caminhao);
        return modelMapper.map(caminhao, DadosExibicaoCaminhaoDTO.class);
    }

    public DadosExibicaoMotocicletaDTO registrarVeiculoMotocicleta(DadosEntradaMotocicletaDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Motocicleta motocicleta = new Motocicleta(cadastro);
        motocicleta.setLeilao(leilao.get());
        motocicletaRepository.save(motocicleta);
        return modelMapper.map(motocicleta, DadosExibicaoMotocicletaDTO.class);
    }

    public DadosExibicaoUtilitarioDTO registrarVeiculoUtilitario(DadosEntradaUtilitarioDTO cadastro){
        Optional<Leilao> leilao = leilaoRepository.findById(cadastro.getIdLeilao());
        Utilitario utilitario = new Utilitario(cadastro);
        utilitario.setLeilao(leilao.get());
        utilitarioRepository.save(utilitario);
        return modelMapper.map(utilitario, DadosExibicaoUtilitarioDTO.class);
    }

    public List<Object> listarVeiculos(){
        return veiculoRepository.findAll().stream()
                .map(veiculo -> veiculo instanceof Carro ?
                        modelMapper.map((Carro) veiculo, DadosExibicaoCarroDTO.class) :
                        veiculo instanceof Caminhao ?
                                modelMapper.map((Caminhao) veiculo, DadosExibicaoCaminhaoDTO.class) :
                                veiculo instanceof Motocicleta ?
                                        modelMapper.map((Motocicleta) veiculo, DadosExibicaoMotocicletaDTO.class) :
                                        modelMapper.map((Utilitario) veiculo, DadosExibicaoUtilitarioDTO.class))
                .collect(Collectors.toList());
    }

    public DadosExibicaoCarroDTO atualizarVeiculoCarro(Long idVeiculo, DadosAtualizacaoCarroDTO atualizacaoCarro){
        Object veiculoEncontrado = carroRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo não existe"));
        if (veiculoEncontrado instanceof Carro){
            Carro carro = new Carro((Carro) veiculoEncontrado, atualizacaoCarro);
            carroRepository.update(carro);
            return modelMapper.map(carro, DadosExibicaoCarroDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um carro");
        }
    }

    public DadosExibicaoCaminhaoDTO atualizarVeiculoCaminhao(Long idVeiculo, DadosAtualizacaoCaminhaoDTO atualizacaoCaminhao){
        Object veiculoEncontrado = caminhaoRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo não existe"));
        if (veiculoEncontrado instanceof Caminhao){
            Caminhao caminhao = new Caminhao((Caminhao) veiculoEncontrado, atualizacaoCaminhao);
            caminhaoRepository.update(caminhao);
            return modelMapper.map(caminhao, DadosExibicaoCaminhaoDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um caminhão");
        }
    }


    //parei aqui 
    public DadosExibicaoMotocicletaDTO atualizarVeiculoMotocicleta(Long idVeiculo, DadosAtualizacaoMotocicletaDTO atualizacaoMotocicleta){
        Object veiculoEncontrado = caminhaoRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo não existe"));
        if (veiculoEncontrado instanceof Caminhao){
            Caminhao caminhao = new Caminhao((Caminhao) veiculoEncontrado, atualizacaoCaminhao);
            caminhaoRepository.update(caminhao);
            return modelMapper.map(caminhao, DadosExibicaoCaminhaoDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é um caminhão");
        }
    }

    public void deletarVeiculo(Long idVeiculo){
        veiculoRepository.deleteById(idVeiculo);
    }

    public void manipularVeiculoLeilao(Long idVeiculo, Long idLeilao){
        Optional<Veiculo> veiculo = veiculoRepository.findById(idVeiculo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        if (!veiculo.get().getLances().isEmpty()){
            throw new CustomException("Ação não permitida. Esse veiculo ja recebeu lance");
        }else {
            veiculo.get().setLeilao(leilaoEncontrado.get());
            veiculoRepository.update(veiculo.get());
        }

    }
}
