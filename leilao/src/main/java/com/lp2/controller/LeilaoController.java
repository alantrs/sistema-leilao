package com.lp2.controller;

import com.lp2.model.dispositivo.DadosEntradaDispositivoDTO;
import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
import com.lp2.model.leilao.DadosEntradaLeilaoDTO;
import com.lp2.model.leilao.DadosExibicaoLeilaoDTO;
import com.lp2.service.LeilaoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller(value = "/leiloes")
@Tag(name = "LEILAO")
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    @Post(uri = "/criar")
    @Operation(summary = "Salvar um leilao")
    @Transactional
    public HttpResponse<DadosExibicaoLeilaoDTO> salvarLeilao(@Body DadosEntradaLeilaoDTO dadosEntradaLeilaoDTO){
        DadosExibicaoLeilaoDTO dados = leilaoService.salvarLeilao(dadosEntradaLeilaoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar leiloes")
    public HttpResponse<List<DadosExibicaoLeilaoDTO>> listarLeiloes(){
        List<DadosExibicaoLeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return HttpResponse.ok().body(leiloes);
    }

    @Delete(uri = "/deletar/{idLeilao}")
    @Operation(summary = "Deletar um leilao")
    @Transactional
    public HttpResponse deletarLeilao(@PathVariable (value = "idLeilao") Long idLeilao){
        leilaoService.deletarLeilao(idLeilao);
        return HttpResponse.ok();
    }
}
