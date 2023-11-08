package com.lp2.controller;

import com.lp2.dto.lance.DadosEntradaLanceDTO;
import com.lp2.service.LanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.transaction.annotation.Transactional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Controller(value = "/lance")
@Tag(name = "LANCE")
public class LanceController {

    @Inject
    private LanceService lanceService;

    @Post(uri = "/realizar-lance-dispositivo/{idDispositivo}")
    @Operation(summary = "Realizar lance")
    @Transactional
    public HttpResponse realizarLanceDispositivo(@PathVariable(value = "idDispositivo") Long idDispositivo, @Body DadosEntradaLanceDTO lance){
        lanceService.realizarLanceVeiculo(idDispositivo, lance);
        return HttpResponse.ok();
    }

    @Post(uri = "/realizar-lance-veiculo/{idVeiculo}")
    @Operation(summary = "Realizar lance")
    @Transactional
    public HttpResponse realizarLance(@PathVariable(value = "idVeiculo") Long idVeiculo, @Body DadosEntradaLanceDTO lance){
        lanceService.realizarLanceVeiculo(idVeiculo, lance);
        return HttpResponse.ok();
    }

}
