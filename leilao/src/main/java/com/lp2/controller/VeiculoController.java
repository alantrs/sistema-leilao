package com.lp2.controller;

import com.lp2.dominio.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.veiculo.DadosExibicaoVeiculo;
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

    @Post(uri = "/criar")
    @Operation(summary = "Salvar um veiculo")
    @Transactional
    public HttpResponse<DadosExibicaoVeiculo> salvarVeiculo(@Body DadosEntradaVeiculo dadosEntradaVeiculo){
        DadosExibicaoVeiculo dados = veiculoService.salvarVeiculo(dadosEntradaVeiculo);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar veiculos")
    public HttpResponse<List<DadosExibicaoVeiculo>> listarVeiculos(){
        List<DadosExibicaoVeiculo> veiculosEncontrados = veiculoService.listarVeiculos();
        return HttpResponse.ok().body(veiculosEncontrados);
    }

    @Put(uri = "/atualizar/{idVeiculo}")
    @Operation(summary = "Atualizar um veiculo")
    @Transactional
    public HttpResponse<DadosExibicaoVeiculo> atualizarVeiculo(@PathVariable(value = "idVeiculo") Long idVeiculo, @Body DadosEntradaVeiculo dadosEntradaVeiculo){
        DadosExibicaoVeiculo veiculo = veiculoService.atualizarVeiculo(idVeiculo, dadosEntradaVeiculo);
        return HttpResponse.ok().body(veiculo);
    }

    @Delete(uri = "/deletar/{idVeiculo}")
    @Operation(summary = "Deletar um veiculo")
    @Transactional
    public HttpResponse deletarVeiculo(@PathVariable(value = "idVeiculo") Long idVeiculo){
        veiculoService.deletarVeiculo(idVeiculo);
        return HttpResponse.ok();
    }
}
