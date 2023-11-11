package com.lp2.service;

import com.lp2.dto.dispositivo.DadosExibicaoSwitchDTO;
import com.lp2.dto.veiculo.*;
import com.lp2.exception.CustomException;
import com.lp2.mapper.VeiculoMapper;
import com.lp2.model.*;
import com.lp2.repository.LeilaoRepository;
import com.lp2.repository.VeiculoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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
    VeiculoMapper veiculoMapper = new VeiculoMapper(modelMapper);

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
        List<Object> veiculosDTO = new ArrayList<>();

        veiculoRepository.findAll().forEach(veiculo -> {
            Object veiculoDTO = veiculoMapper.mapearVeiculoParaDTO(veiculo);
            veiculosDTO.add(veiculoDTO);
        });
        return veiculosDTO;
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

    public DadosExibicaoMotocicletaDTO atualizarVeiculoMotocicleta(Long idVeiculo, DadosAtualizacaoMotocicletaDTO atualizacaoMotocicleta){
        Object veiculoEncontrado = motocicletaRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo não existe"));
        if (veiculoEncontrado instanceof Motocicleta){
            Motocicleta motocicleta = new Motocicleta((Motocicleta) veiculoEncontrado, atualizacaoMotocicleta);
            motocicletaRepository.update(motocicleta);
            return modelMapper.map(motocicleta, DadosExibicaoMotocicletaDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é uma motocicleta");
        }
    }

    public DadosExibicaoUtilitarioDTO atualizarVeiculoUtilitario(Long idVeiculo, DadosAtualizacaoUtilitarioDTO atualizacaoUtilitario){
        Object veiculoEncontrado = utilitarioRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo não existe"));
        if (veiculoEncontrado instanceof Utilitario){
            Utilitario utilitario = new Utilitario((Utilitario) veiculoEncontrado, atualizacaoUtilitario);
            utilitarioRepository.update(utilitario);
            return modelMapper.map(utilitario, DadosExibicaoUtilitarioDTO.class);
        }else {
            throw new CustomException("Não foi possivel atualizar. Id passado não é uma utilitario");
        }
    }

    public void deletarVeiculo(Long idVeiculo){
        veiculoRepository.deleteById(idVeiculo);
    }

    public void manipularVeiculoLeilao(Long idVeiculo, Long idLeilao){
        Optional<Veiculo> veiculo = veiculoRepository.findById(idVeiculo);
        Optional<Leilao> leilaoEncontrado = leilaoRepository.findById(idLeilao);

        if (!veiculo.get().getLances().isEmpty()){
            throw new CustomException("Acao nao permitida. Esse veiculo ja recebeu lance");
        }else {
            veiculo.get().setLeilao(leilaoEncontrado.get());
            veiculoRepository.update(veiculo.get());
        }

    }
}
