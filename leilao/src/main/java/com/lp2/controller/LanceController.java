package com.lp2.controller;

import com.lp2.dto.lance.DadosEntradaLanceDTO;
import com.lp2.dto.lance.DadosExibicaoLanceProdutoDTO;
import com.lp2.enums.TipoProduto;
import com.lp2.service.LanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

@Controller(value = "/lance")
@Tag(name = "LANCE")
public class LanceController {

    @Inject
    private LanceService lanceService;

    @Post(uri = "/realizar-lance/{idProduto}")
    @Operation(summary = "Realizar lance a um produto")
    @Transactional
    public HttpResponse realizarLance(@PathVariable(value = "idProduto") Long idProduto, @Body DadosEntradaLanceDTO lance, @QueryValue TipoProduto tipoProduto){
        lanceService.realizarLanceProduto(idProduto, lance, tipoProduto);
        return HttpResponse.ok();
    }

    @Get(uri = "/listar-lance-produto/{idProduto}")
    @Operation(summary = "Lista os lances registrados a um produto")
    public HttpResponse<DadosExibicaoLanceProdutoDTO> listarLancesProdutos(@PathVariable(value = "idProduto") Long idProduto, @QueryValue TipoProduto tipoProduto){
        return HttpResponse.ok().body(lanceService.listarLancesProduto(idProduto, tipoProduto));
    }
}
