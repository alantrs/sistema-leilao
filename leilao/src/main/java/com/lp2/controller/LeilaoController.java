package com.lp2.controller;

import com.lp2.dto.leilao.DadosAtualizacaoLeilaoDTO;
import com.lp2.dto.leilao.DadosEntradaLeilaoDTO;
import com.lp2.dto.leilao.DadosExibicaoDadosDetalhadosLeilaoDTO;
import com.lp2.dto.leilao.DadosExibicaoDadosResumidosLeilaoDTO;
import com.lp2.service.LeilaoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.util.List;

@Controller(value = "/leiloes")
@Tag(name = "LEILAO")
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    @Post(uri = "/registrar-leilao")
    @Operation(summary = "Registrar um leilao")
    @Transactional
    public HttpResponse<DadosExibicaoDadosResumidosLeilaoDTO> salvarLeilao(@Body DadosEntradaLeilaoDTO dadosEntradaLeilaoDTO){
        DadosExibicaoDadosResumidosLeilaoDTO dados = leilaoService.salvarLeilao(dadosEntradaLeilaoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar leiloes com informacoes resumidas ordenados por data de ocorrencia")
    public HttpResponse<List<DadosExibicaoDadosResumidosLeilaoDTO>> listarLeiloes(){
        List<DadosExibicaoDadosResumidosLeilaoDTO> leiloes = leilaoService.listarLeiloes();
        return HttpResponse.ok().body(leiloes);
    }

    @Get(uri = "/buscar/{idLeilao}")
    @Operation(summary = "Buscar um leilao pelo id, mostrando mais detalhes como veiculos/dispositivos, entidade financeira")
    @Transactional
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
