package com.lp2.controller;

import com.lp2.dto.entidadeFinanceira.DadosAtualizaEntidadeFinanceiraDTO;
import com.lp2.dto.entidadeFinanceira.DadosEntradaEntidadeFinanceiraDTO;
import com.lp2.dto.entidadeFinanceira.DadosExibicaoEntidadeFinanceiraDTO;
import com.lp2.service.EntidadeFinanceiraService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller(value = "/entidade")
@Tag(name = "ENTIDADE FINANCEIRA")
public class EntidadeController {

    @Inject
    private EntidadeFinanceiraService entidadeFinanceiraService;

    @Post(uri = "/registrar-entidade")
    @Operation(summary = "Registrar uma entidade financeira")
    @Transactional
    public HttpResponse<DadosExibicaoEntidadeFinanceiraDTO> salvarEntidade(@Body DadosEntradaEntidadeFinanceiraDTO cadastro){
        DadosExibicaoEntidadeFinanceiraDTO entidade = entidadeFinanceiraService.salvarEntidadeFinaceira(cadastro);
        return HttpResponse.status(HttpStatus.CREATED).body(entidade);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar entidades")
    public HttpResponse<List<DadosExibicaoEntidadeFinanceiraDTO>> listarEntidades(){
        List<DadosExibicaoEntidadeFinanceiraDTO> entidades = entidadeFinanceiraService.listarEntidadesFinanceiras();
        return HttpResponse.ok().body(entidades);
    }

    @Put(uri = "/atualizar/{idEntidadeFinanceira}")
    @Operation(summary = "Atualizar uma entidade")
    @Transactional
    public HttpResponse<DadosExibicaoEntidadeFinanceiraDTO> atualizarEntidadeFinanceira(@PathVariable(value = "idEntidadeFinanceira") Long idEntidadeFinanceira, @Body DadosAtualizaEntidadeFinanceiraDTO atualizacao){
        DadosExibicaoEntidadeFinanceiraDTO entidadeAtualizada = entidadeFinanceiraService.atualizarEntidadeFinanceira(idEntidadeFinanceira, atualizacao);
        return HttpResponse.ok().body(entidadeAtualizada);
    }

    @Delete(uri = "/deletar/{idEntidadeFinanceira}")
    @Operation(summary = "Deleta uma entidade")
    @Transactional
    public HttpResponse deletarEntidade(@PathVariable(value = "idEntidadeFinanceira") Long idEntidadeFinanceira){
        entidadeFinanceiraService.deletarEntidade(idEntidadeFinanceira);
        return HttpResponse.ok();
    }


}
