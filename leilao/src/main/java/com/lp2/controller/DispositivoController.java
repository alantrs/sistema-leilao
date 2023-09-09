package com.lp2.controller;

import com.lp2.dominio.dispositivo.DadosEntradaDispositivo;
import com.lp2.dominio.dispositivo.DadosExibicaoDispositivo;
import com.lp2.service.DispositivoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Controller(value = "/dispositivos")
@Tag(name = "DISPOSITIVO")
public class DispositivoController {

    @Inject
    private DispositivoService dispositivoInformaticaService;

    @Post(uri = "/criar-um-dispositivo")
    @Operation(summary = "Salvar um dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivo> salvarDispositivo(@Body DadosEntradaDispositivo dadosEntradaDispositivo){
        DadosExibicaoDispositivo dados = dispositivoInformaticaService.salvarDispositivo(dadosEntradaDispositivo);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }


    @Post(uri = "/criar-varios-dispositivos")
    @Operation(summary = "Salvar varios dispositivos")
    @Transactional
    public HttpResponse<List<DadosExibicaoDispositivo>> salvarDispositivos(@Body List<DadosEntradaDispositivo> cadastro){
        List<DadosExibicaoDispositivo> dispositivosSalvos = dispositivoInformaticaService.salvarDispositivos(cadastro);
        return HttpResponse.status(HttpStatus.CREATED).body(dispositivosSalvos);
    }



    @Get(uri = "/listar")
    @Operation(summary = "Listar dispositivos")
    public HttpResponse<List<DadosExibicaoDispositivo>> listarDispositivos(){
        List<DadosExibicaoDispositivo> dispositivosEncontrados = dispositivoInformaticaService.listarDispositivos();
        return HttpResponse.ok().body(dispositivosEncontrados);
    }

    @Put(uri = "/atualizar/{idDispositivo}")
    @Operation(summary = "Atualizar um dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivo> atualizarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo, @Body DadosEntradaDispositivo dadosEntradaDispositivo){
        DadosExibicaoDispositivo dispositivo = dispositivoInformaticaService.atualizarDispositivo(idDispositivo, dadosEntradaDispositivo);
        return HttpResponse.noContent().body(dispositivo);
    }

    @Delete(uri = "/deletar/{idDispositivo}")
    @Operation(summary = "Deletar um dispositivo")
    @Transactional
    public HttpResponse deletarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo){
        dispositivoInformaticaService.deletarDispositivo(idDispositivo);
        return HttpResponse.noContent();
    }
}

