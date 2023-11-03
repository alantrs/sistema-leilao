package com.lp2.service;

import com.lp2.model.Notebook;
import com.lp2.repository.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LanceService {

    @Inject
    private LanceRepository lanceRepository;
    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private DispositivoRepository<Notebook> notebookRepository;
    @Inject
    private VeiculoRepository veiculoRepository;

//    public void realizarLance(Long idProduto, DadosEntradaLanceDTO lance){
//        Optional<Cliente> cliente = clienteRepository.findById(lance.getIdCliente());
//        DispositivoInformatica dispositivoInformatica = notebookRepository.findById(idProduto).orElse(null);
//        Veiculo veiculo = veiculoRepository.findById(idProduto).orElse(null);
//
//        if (dispositivoInformatica != null){
//            Lance lanceRealizado = new Lance(lance);
//            lanceRealizado.setCliente(cliente.get());
//            lanceRealizado.setDispositivoInformatica(dispositivoInformatica);
//            lanceRepository.save(lanceRealizado);
//        } else if (veiculo != null){
//            Lance lanceRealizado = new Lance(lance);
//            lanceRealizado.setCliente(cliente.get());
//            lanceRealizado.setVeiculo(veiculo);
//            lanceRepository.save(lanceRealizado);
//        }
//    }
}
