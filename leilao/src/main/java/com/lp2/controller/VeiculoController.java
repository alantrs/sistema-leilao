package com.lp2.controller;

import com.lp2.dominio.dto.veiculo.DadosEntradaVeiculo;
import com.lp2.dominio.dto.veiculo.DadosExibicaoVeiculo;
import com.lp2.service.VeiculoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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

}
