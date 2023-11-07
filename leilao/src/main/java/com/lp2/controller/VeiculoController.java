package com.lp2.controller;

import com.lp2.dto.veiculo.*;
import com.lp2.service.VeiculoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller(value = "/veiculos")
@Tag(name = "VEICULO")
public class VeiculoController {

    @Inject
    private VeiculoService veiculoService;

    @Post(uri = "/registrar-carro")
    @Operation(summary = "Registrar um carro")
    @Transactional
    public HttpResponse<DadosExibicaoCarroDTO> registrarVeiculoCarro(@Body DadosEntradaCarroDTO carro){
        DadosExibicaoCarroDTO dados = veiculoService.registrarVeiculoCarro(carro);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-motocicleta")
    @Operation(summary = "Registrar uma motocicleta")
    @Transactional
    public HttpResponse<DadosExibicaoMotocicletaDTO> registrarVeiculoMotocicleta(@Body DadosEntradaMotocicletaDTO motocicleta){
        DadosExibicaoMotocicletaDTO dados = veiculoService.registrarVeiculoMotocicleta(motocicleta);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-caminhao")
    @Operation(summary = "Registrar um caminhao")
    @Transactional
    public HttpResponse<DadosExibicaoCaminhaoDTO> registrarVeiculoCaminhao(@Body DadosEntradaCaminhaoDTO caminhao){
        DadosExibicaoCaminhaoDTO dados = veiculoService.registrarVeiculoCaminhao(caminhao);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-utilitario")
    @Operation(summary = "Registrar um utilitario")
    @Transactional
    public HttpResponse<DadosExibicaoUtilitarioDTO> registrarVeiculoUtilitario(@Body DadosEntradaUtilitarioDTO utilitario){
        DadosExibicaoUtilitarioDTO dados = veiculoService.registrarVeiculoUtilitario(utilitario);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar veiculos")
    public HttpResponse<List<Object>> listarVeiculos(){
        List<Object> veiculosEncontrados = veiculoService.listarVeiculos();
        return HttpResponse.ok().body(veiculosEncontrados);
    }

//    @Put(uri = "/atualizar/{idVeiculo}")
//    @Operation(summary = "Atualizar um veiculo")
//    @Transactional
//    public HttpResponse<DadosExibicaoVeiculoDTO> atualizarVeiculo(@PathVariable(value = "idVeiculo") Long idVeiculo, @Body DadosAtualizacaoVeiculoDTO atualizacao){
//        DadosExibicaoVeiculoDTO veiculo = veiculoService.atualizarVeiculo(idVeiculo, atualizacao);
//        return HttpResponse.ok().body(veiculo);
//    }

    @Delete(uri = "/deletar/{idVeiculo}")
    @Operation(summary = "Deletar um veiculo")
    @Transactional
    public HttpResponse deletarVeiculo(@PathVariable(value = "idVeiculo") Long idVeiculo){
        veiculoService.deletarVeiculo(idVeiculo);
        return HttpResponse.ok();
    }

    @Put(uri = "/manipular/{idVeiculo}")
    @Transactional
    @Operation(summary = "Manipular um veiculo a um leilao")
    public HttpResponse manipularVeiculoLeilao(@PathVariable(value = "idVeiculo") Long idVeiculo, @QueryValue Long idLeilao){
        veiculoService.manipularVeiculoLeilao(idVeiculo, idLeilao);
        return HttpResponse.ok();
    }
}
