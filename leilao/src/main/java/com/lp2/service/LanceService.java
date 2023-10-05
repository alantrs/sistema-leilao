package com.lp2.service;

import com.lp2.model.cliente.Cliente;
import com.lp2.model.dispositivo.DispositivoInformatica;
import com.lp2.model.lance.DadosEntradaLanceDTO;
import com.lp2.model.lance.Lance;
import com.lp2.model.leilao.Leilao;
import com.lp2.model.veiculo.Veiculo;
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
    private DispositivoRepository dispositivoRepository;
    @Inject
    private VeiculoRepository veiculoRepository;

    public void realizarLance(Long idProduto, DadosEntradaLanceDTO lance){
        Optional<Cliente> cliente = clienteRepository.findById(lance.getIdCliente());
        Optional<DispositivoInformatica> dispositivoInformatica = dispositivoRepository.findById(idProduto);
        Optional<Veiculo> veiculo = veiculoRepository.findById(idProduto);

        if (dispositivoInformatica != null){
            Lance lanceRealizado = new Lance(lance);
            lanceRealizado.setCliente(cliente.get());
            lanceRealizado.setDispositivoInformatica(dispositivoInformatica.get());
            lanceRepository.save(lanceRealizado);
        } else if (veiculo != null){
            Lance lanceRealizado = new Lance(lance);
            lanceRealizado.setCliente(cliente.get());
            lanceRealizado.setVeiculo(veiculo.get());
            lanceRepository.save(lanceRealizado);
        }
    }
}
