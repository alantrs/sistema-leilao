package com.lp2.controller;

import com.lp2.dto.cliente.DadosAtualizacaoClienteDTO;
import com.lp2.dto.cliente.DadosEntradaClienteDTO;
import com.lp2.dto.cliente.DadosExibicaoClienteDTO;
import com.lp2.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller(value = "/cliente")
@Tag(name = "CLIENTE")
public class ClienteController {

    @Inject
    private ClienteService clienteService;

    @Post(uri = "/registrar-cliente")
    @Operation(summary = "Registar um cliente")
    @Transactional
    public HttpResponse<DadosExibicaoClienteDTO> salvarCliente(@Body DadosEntradaClienteDTO cadastro){
        DadosExibicaoClienteDTO cliente = clienteService.salvarCliente(cadastro);
        return HttpResponse.status(HttpStatus.CREATED).body(cliente);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar clientes")
    public HttpResponse<List<DadosExibicaoClienteDTO>> listarClientes(){
        List<DadosExibicaoClienteDTO> clientes = clienteService.listarClientes();
        return HttpResponse.ok().body(clientes);
    }

    @Put(uri = "/atualizar/{idCliente}")
    @Operation(summary = "Atualizar um cliente")
    @Transactional
    public HttpResponse<DadosExibicaoClienteDTO> atualizarCliente(@PathVariable(value = "idCliente") Long idCliente, @Body DadosAtualizacaoClienteDTO atualizacao){
        DadosExibicaoClienteDTO clienteAtualizado = clienteService.atualizarCliente(idCliente, atualizacao);
        return HttpResponse.ok().body(clienteAtualizado);
    }


    @Delete(uri = "/deletar/{idCliente}")
    @Operation(summary = "Deletar um cliente")
    @Transactional
    public HttpResponse deletarCliente(@PathVariable(value = "idCliente")Long idCliente){
        clienteService.deletarCLiente(idCliente);
        return HttpResponse.ok();
    }

}
