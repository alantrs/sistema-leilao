package com.lp2.controller;

import com.lp2.service.LanceService;
import io.micronaut.http.annotation.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Controller(value = "/lance")
@Tag(name = "LANCE")
public class LanceController {

    @Inject
    private LanceService lanceService;

//    @Post(uri = "/realizar-lance/{idProduto}")
//    @Operation(summary = "Realizar lance")
//    @Transactional
//    public HttpResponse realizarLance(@PathVariable(value = "idProduto") Long idProduto, @Body DadosEntradaLanceDTO lance){
//        lanceService.realizarLance(idProduto, lance);
//        return HttpResponse.ok();
//    }
}
