package com.lp2.service;

import com.lp2.model.cliente.Cliente;
import com.lp2.model.cliente.DadosAtualizacaoClienteDTO;
import com.lp2.model.cliente.DadosEntradaClienteDTO;
import com.lp2.model.cliente.DadosExibicaoClienteDTO;
import com.lp2.repository.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    public DadosExibicaoClienteDTO salvarCliente(DadosEntradaClienteDTO cadastro){
        Cliente cliente = new Cliente(cadastro);
        clienteRepository.save(cliente);
        return new DadosExibicaoClienteDTO(cliente);
    }

    public List<DadosExibicaoClienteDTO> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new DadosExibicaoClienteDTO(cliente)).toList();
    }

    public DadosExibicaoClienteDTO atualizarCliente(Long idCliente, DadosAtualizacaoClienteDTO atualizacao){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(idCliente);

        Cliente cliente = new Cliente(clienteEncontrado.get(), atualizacao);
        clienteRepository.update(cliente);
        return new DadosExibicaoClienteDTO(cliente);
    }

    public void deletarCLiente(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }

}
