package com.lp2.controller;

import com.lp2.model.veiculo.DadosEntradaVeiculoDTO;
import com.lp2.model.veiculo.DadosExibicaoVeiculoDTO;
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
    public HttpResponse<DadosExibicaoVeiculoDTO> salvarVeiculo(@Body DadosEntradaVeiculoDTO dadosEntradaVeiculoDTO){
        DadosExibicaoVeiculoDTO dados = veiculoService.salvarVeiculo(dadosEntradaVeiculoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar veiculos")
    public HttpResponse<List<DadosExibicaoVeiculoDTO>> listarVeiculos(){
        List<DadosExibicaoVeiculoDTO> veiculosEncontrados = veiculoService.listarVeiculos();
        return HttpResponse.ok().body(veiculosEncontrados);
    }

    @Put(uri = "/atualizar/{idVeiculo}")
    @Operation(summary = "Atualizar um veiculo")
    @Transactional
    public HttpResponse<DadosExibicaoVeiculoDTO> atualizarVeiculo(@PathVariable(value = "idVeiculo") Long idVeiculo, @Body DadosEntradaVeiculoDTO dadosEntradaVeiculoDTO){
        DadosExibicaoVeiculoDTO veiculo = veiculoService.atualizarVeiculo(idVeiculo, dadosEntradaVeiculoDTO);
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
