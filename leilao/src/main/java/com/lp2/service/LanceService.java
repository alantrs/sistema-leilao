package com.lp2.service;

import com.lp2.dto.lance.DadosEntradaLanceDTO;
import com.lp2.dto.lance.DadosExibicaoLanceProdutoDTO;
import com.lp2.enums.TipoProduto;
import com.lp2.exception.CustomException;
import com.lp2.model.*;
import com.lp2.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Singleton
public class LanceService {

    @Inject
    private LanceRepository lanceRepository;
    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private DispositivoRepository<DispositivoInformatica> dispositivoRepository;
    @Inject
    private VeiculoRepository<Veiculo> veiculoRepository;
    ModelMapper modelMapper = new ModelMapper();

    public void realizarLanceProduto(Long idProduto, DadosEntradaLanceDTO lance, TipoProduto tipoProduto){
        if (tipoProduto.equals(TipoProduto.DISPOSITIVO)){
            realizarLanceDispositivo(idProduto, lance);
        }else if(tipoProduto.equals(TipoProduto.VEICULO)){
            realizarLanceVeiculo(idProduto, lance);
        }
    }

    private void realizarLanceDispositivo(Long idDispositivo, DadosEntradaLanceDTO lance){
        Cliente cliente = clienteRepository.findById(lance.getIdCliente()).orElseThrow(()-> new CustomException("Cliente nao existe"));
        DispositivoInformatica dispositivoEncontrado = dispositivoRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("Dispositivo nao existe"));

        if (dispositivoEncontrado.getValorInicial().compareTo(lance.getValor()) > 0){
            throw new CustomException("Valor deve ser igual ou superior ao valor inicial");
        }

        Lance lanceRealizado = new Lance(lance);
        lanceRealizado.setCliente(cliente);
        lanceRealizado.setDispositivoInformatica(dispositivoEncontrado);
        lanceRepository.save(lanceRealizado);
    }

    private void realizarLanceVeiculo(Long idVeiculo, DadosEntradaLanceDTO lance){
        Cliente cliente = clienteRepository.findById(lance.getIdCliente()).orElseThrow(()-> new CustomException("Cliente nao existe"));
        Veiculo veiculoEncontrado = veiculoRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo nao existe"));

        if (veiculoEncontrado.getValorInicial().compareTo(lance.getValor()) > 0){
            throw new CustomException("Valor deve ser igual ou superior ao valor inicial");
        }
        Lance lanceRealizado = new Lance(lance);
        lanceRealizado.setCliente(cliente);
        lanceRealizado.setVeiculo(veiculoEncontrado);
        lanceRepository.save(lanceRealizado);
    }

    public DadosExibicaoLanceProdutoDTO listarLancesProduto(Long idProduto, TipoProduto tipoProduto){

        if (tipoProduto.equals(TipoProduto.DISPOSITIVO)){
            List<Lance> lances = lanceRepository.findAllByDispositivoInformaticaId(idProduto);
            DadosExibicaoLanceProdutoDTO dadosProduto = new DadosExibicaoLanceProdutoDTO(lances);
            dadosProduto.setProduto(lances.get(0).getDispositivoInformatica().getNome());
            return dadosProduto;
        }else if(tipoProduto.equals(TipoProduto.VEICULO)){
            List<Lance> lances = lanceRepository.findAllByVeiculoId(idProduto);
            DadosExibicaoLanceProdutoDTO dadosProduto = new DadosExibicaoLanceProdutoDTO(lances);
            dadosProduto.setProduto(lances.get(0).getVeiculo().getModelo());
            return dadosProduto;
        }

        return null;
    }
}
