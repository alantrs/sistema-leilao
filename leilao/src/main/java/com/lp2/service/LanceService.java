package com.lp2.service;

import com.lp2.dto.lance.DadosExibicaoLanceProdutoDTO;
import com.lp2.enums.TipoProduto;
import com.lp2.exception.CustomException;
import com.lp2.model.*;
import com.lp2.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

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

    public void realizarLanceProduto(Long idProduto, Long idCliente, BigDecimal valor, TipoProduto tipoProduto){
        if (tipoProduto.equals(TipoProduto.DISPOSITIVO)){
            realizarLanceDispositivo(idProduto, idCliente, valor);
        }else if(tipoProduto.equals(TipoProduto.VEICULO)){
            realizarLanceVeiculo(idProduto, idCliente, valor);
        }
    }

    private void realizarLanceDispositivo(Long idDispositivo, Long idCliente, BigDecimal valor){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new CustomException("Cliente nao existe"));
        DispositivoInformatica dispositivoEncontrado = dispositivoRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("Dispositivo nao existe"));

        if (dispositivoEncontrado.getValorInicial().compareTo(valor) > 0){
            throw new CustomException("Valor deve ser igual ou superior ao valor inicial");
        }

        Lance lanceRealizado = new Lance(valor);
        lanceRealizado.setCliente(cliente);
        lanceRealizado.setDispositivoInformatica(dispositivoEncontrado);
        lanceRepository.save(lanceRealizado);
    }

    private void realizarLanceVeiculo(Long idVeiculo, Long idCliente, BigDecimal valor){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new CustomException("Cliente nao existe"));
        Veiculo veiculoEncontrado = veiculoRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo nao existe"));

        if (veiculoEncontrado.getValorInicial().compareTo(valor) > 0){
            throw new CustomException("Valor deve ser igual ou superior ao valor inicial");
        }
        Lance lanceRealizado = new Lance(valor);
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
