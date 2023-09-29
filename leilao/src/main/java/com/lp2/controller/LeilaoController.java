package com.lp2.controller;

import com.lp2.model.leilao.DadosAtualizacaoLeilaoDTO;
import com.lp2.model.leilao.DadosEntradaLeilaoDTO;
import com.lp2.model.leilao.DadosExibicaoDadosDetalhadosLeilaoDTO;
import com.lp2.model.leilao.DadosExibicaoDadosResumidosLeilaoDTO;
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
    public HttpResponse<DadosExibicaoDadosResumidosLeilaoDTO> salvarLeilao(@Body DadosEntradaLeilaoDTO dadosEntradaLeilaoDTO){
        DadosExibicaoDadosResumidosLeilaoDTO dados = leilaoService.salvarLeilao(dadosEntradaLeilaoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar leiloes")
    public HttpResponse<List<DadosExibicaoDadosResumidosLeilaoDTO>> listarLeiloes(){
        List<DadosExibicaoDadosResumidosLeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return HttpResponse.ok().body(leiloes);
    }
    @Get(uri = "/buscar/{idLeilao}")
    @Operation(summary = "Buscar um leilao pelo id")
    public HttpResponse<DadosExibicaoDadosDetalhadosLeilaoDTO> buscarLeilao(@PathVariable(value = "idLeilao") Long idLeilao){
        DadosExibicaoDadosDetalhadosLeilaoDTO leilaoEncontrado = leilaoService.exibirInformacoesLeilao(idLeilao);
        return HttpResponse.ok().body(leilaoEncontrado);
    }

    @Delete(uri = "/deletar/{idLeilao}")
    @Operation(summary = "Deletar um leilao")
    @Transactional
    public HttpResponse deletarLeilao(@PathVariable (value = "idLeilao") Long idLeilao){
        leilaoService.deletarLeilao(idLeilao);
        return HttpResponse.ok();
    }

    @Put(uri = "/atualizar/{idLeilao}")
    @Operation(summary = "Atualizar um leilao")
    @Transactional
    public HttpResponse<DadosExibicaoDadosResumidosLeilaoDTO> atualizarLeilao(@PathVariable(value = "idLeilao") Long idLeilao, DadosAtualizacaoLeilaoDTO atualizacao){
        DadosExibicaoDadosResumidosLeilaoDTO leilaoAtualizado = leilaoService.atualizarLeilao(idLeilao, atualizacao);
        return HttpResponse.ok().body(leilaoAtualizado);
    }
}