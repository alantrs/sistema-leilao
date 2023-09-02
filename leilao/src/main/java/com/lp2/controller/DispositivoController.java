package com.lp2.controller;

import com.lp2.dominio.dto.dispositivo.DadosEntradaDispositivo;
import com.lp2.dominio.dto.dispositivo.DadosExibicaoDispositivo;
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

    @Post(uri = "/criar")
    @Operation(summary = "Salvar dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivo> salvarDispositivo(@Body DadosEntradaDispositivo dadosEntradaDispositivo){
        DadosExibicaoDispositivo dados = dispositivoInformaticaService.salvarDispositivo(dadosEntradaDispositivo);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar dispositivos")
    public HttpResponse<List<DadosExibicaoDispositivo>> listarDispositivos(){
        List<DadosExibicaoDispositivo> dispositivosEncontrados = dispositivoInformaticaService.listarDispositivos();
        return HttpResponse.ok().body(dispositivosEncontrados);
    }

    @Put(uri = "/atualizar/{idDispositivo}")
    @Operation(summary = "Atualizar um dispositivo")
    public HttpResponse<DadosExibicaoDispositivo> atualizarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo, @Body DadosEntradaDispositivo dadosEntradaDispositivo){
        DadosExibicaoDispositivo dispositivo = dispositivoInformaticaService.atualizarDispositivo(idDispositivo, dadosEntradaDispositivo);
        return HttpResponse.ok().body(dispositivo);
    }

    @Delete(uri = "/deletar/{idDispositivo}")
    @Operation(summary = "Deletar um dispositivo")
    public HttpResponse deletarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo){
        dispositivoInformaticaService.deletarDispositivo(idDispositivo);
        return HttpResponse.ok();
    }
}

