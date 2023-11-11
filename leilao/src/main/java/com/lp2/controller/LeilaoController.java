package com.lp2.controller;

import com.lp2.dto.leilao.DadosAtualizacaoLeilaoDTO;
import com.lp2.dto.leilao.DadosEntradaLeilaoDTO;
import com.lp2.dto.leilao.DadosExibicaoDadosResumidosLeilaoDTO;
import com.lp2.enums.TipoProduto;
import com.lp2.service.LeilaoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.transaction.annotation.Transactional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.List;

@Controller(value = "/leiloes")
@Tag(name = "LEILAO")
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    @Post(uri = "/registrar")
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
    @Operation(summary = "Buscar um leilao pelo id, mostrando mais detalhes como produtos (ordenados por nome) e entidades financeiras")
    @Transactional
    public HttpResponse<Object> buscarLeilao(@PathVariable(value = "idLeilao") Long idLeilao){
        Object leilaoEncontrado = leilaoService.exibirInformacoesLeilao(idLeilao);
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

    @Get(uri = "/produto/{idLeilao}/{idProduto}")
    @Operation(summary = "Busca um produto de um leilao por id")
    public HttpResponse<Object> buscarProdutoLeilao(@PathVariable (value = "idLeilao") Long idLeilao, @PathVariable (value = "idProduto") Long idProduto, @QueryValue TipoProduto tipoProduto){
        return HttpResponse.ok().body(leilaoService.buscarProdutoLeilao(idLeilao, idProduto, tipoProduto));
    }

    @Get(uri = "/produto-faixa-valor/{idLeilao}")
    @Operation(summary = "Busca produtos de um leilao por faixa de valores de lance inicial")
    public HttpResponse<Object> buscarProdutoLeilaoFaixaValor(@PathVariable (value = "idLeilao") Long idLeilao, @QueryValue BigDecimal min, @QueryValue BigDecimal max){
        return HttpResponse.ok().body(leilaoService.buscarProdutosEmLeilaoPorFaixaValor(idLeilao, min, max));
    }

    @Get(uri = "/produto-nome/{idLeilao}")
    @Operation(summary = "Busca produtos de um leilao por palavra chave contida no nome")
    public HttpResponse<Object> buscarProdutosLeilaoPorNome(@PathVariable (value = "idLeilao") Long idLeilao, @QueryValue String nome){
        return HttpResponse.ok().body(leilaoService.buscarProdutoEmLeilaoPorNome(idLeilao, nome));
    }

    @Get(uri = "/produto-tipo/{idLeilao}")
    @Operation(summary = "Busca produtos de um leilao por tipo")
    public HttpResponse<Object> buscarProdutosLeilaoPorTipo(@PathVariable (value = "idLeilao") Long idLeilao, @QueryValue TipoProduto tipoProduto){
        return HttpResponse.ok().body(leilaoService.buscarProdutoEmLeilaoPorTipo(idLeilao, tipoProduto));
    }
}
