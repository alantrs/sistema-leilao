package com.lp2.service;

import com.lp2.dto.lance.DadosEntradaLanceDTO;
import com.lp2.exception.CustomException;
import com.lp2.model.*;
import com.lp2.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

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

    public void realizarLanceDispositivo(Long idDispositivo, DadosEntradaLanceDTO lance){
        Cliente cliente = clienteRepository.findById(lance.getIdCliente()).orElseThrow(()-> new CustomException("Cliente nao existe"));
        DispositivoInformatica dispositivoInformatica = dispositivoRepository.findById(idDispositivo).orElseThrow(() -> new CustomException("Dispositivo nao existe"));

        Lance lanceRealizado = new Lance(lance);
        lanceRealizado.setCliente(cliente);
        lanceRealizado.setDispositivoInformatica(dispositivoInformatica);
        lanceRepository.save(lanceRealizado);
    }

    public void realizarLanceVeiculo(Long idVeiculo, DadosEntradaLanceDTO lance){
        Cliente cliente = clienteRepository.findById(lance.getIdCliente()).orElseThrow(()-> new CustomException("Cliente nao existe"));
        Veiculo veiculoEncontrado = veiculoRepository.findById(idVeiculo).orElseThrow(() -> new CustomException("Veiculo nao existe"));

        Lance lanceRealizado = new Lance(lance);
        lanceRealizado.setCliente(cliente);
        lanceRealizado.setVeiculo(veiculoEncontrado);
        lanceRepository.save(lanceRealizado);
    }
}
