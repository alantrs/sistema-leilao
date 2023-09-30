package com.lp2.service;

import com.lp2.model.cliente.Cliente;
import com.lp2.model.lance.DadosEntradaLanceDTO;
import com.lp2.model.lance.Lance;
import com.lp2.model.leilao.Leilao;
import com.lp2.repository.ClienteRepository;
import com.lp2.repository.LanceRepository;
import com.lp2.repository.LeilaoRepository;
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
    private LeilaoRepository leilaoRepository;

    public void realizarLance(Long idLeilao, DadosEntradaLanceDTO lance){
        Optional<Cliente> cliente = clienteRepository.findById(lance.getIdCliente());
        Optional<Leilao> leilao = leilaoRepository.findById(idLeilao);

        Lance lanceRealizado = new Lance(lance);
        lanceRealizado.setCliente(cliente.get());
        lanceRealizado.setLeilao(leilao.get());
        lanceRepository.save(lanceRealizado);
    }
}
