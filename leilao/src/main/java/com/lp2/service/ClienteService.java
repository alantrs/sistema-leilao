package com.lp2.service;

import com.lp2.model.Cliente;
import com.lp2.dto.cliente.DadosAtualizacaoClienteDTO;
import com.lp2.dto.cliente.DadosEntradaClienteDTO;
import com.lp2.dto.cliente.DadosExibicaoClienteDTO;
import com.lp2.repository.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Singleton
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;
    ModelMapper modelMapper = new ModelMapper();

    public DadosExibicaoClienteDTO salvarCliente(DadosEntradaClienteDTO cadastro){
        Cliente cliente = new Cliente(cadastro);
        clienteRepository.save(cliente);
        return modelMapper.map(cliente, DadosExibicaoClienteDTO.class);
    }

    public List<DadosExibicaoClienteDTO> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> modelMapper.map(cliente, DadosExibicaoClienteDTO.class)).toList();
    }

    public DadosExibicaoClienteDTO atualizarCliente(Long idCliente, DadosAtualizacaoClienteDTO atualizacao){
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(idCliente);

        Cliente cliente = new Cliente(clienteEncontrado.get(), atualizacao);
        clienteRepository.update(cliente);
        return modelMapper.map(cliente, DadosExibicaoClienteDTO.class);
    }

    public void deletarCLiente(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }

}
