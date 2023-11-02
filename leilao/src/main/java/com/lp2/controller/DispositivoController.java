package com.lp2.controller;

import com.lp2.dto.dispositivo.*;
import com.lp2.service.DispositivoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

@Controller(value = "/dispositivos")
@Tag(name = "DISPOSITIVO")
public class DispositivoController {

    @Inject
    private DispositivoService dispositivoInformaticaService;

    @Post(uri = "/registrar-notebook")
    @Operation(summary = "Registrar um notebook")
    @Transactional
    public HttpResponse<DadosExibicaoNotebookDTO> registrarDispositivoNotebook(@Body DadosEntradaNotebookDTO notebook){
        DadosExibicaoNotebookDTO dados = dispositivoInformaticaService.registrarDispositivoNotebook(notebook);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-roteador")
    @Operation(summary = "Registrar um roteador")
    @Transactional
    public HttpResponse<DadosExibicaoRoteadorDTO> registrarDispositivoRoteador(@Body DadosEntradaRoteadorDTO roteador){
        DadosExibicaoRoteadorDTO dados = dispositivoInformaticaService.registrarDispositivoRoteador(roteador);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-switch")
    @Operation(summary = "Registrar um switch")
    @Transactional
    public HttpResponse<DadosExibicaoSwitchDTO> registrarDispositivoSwitch(@Body DadosEntradaSwitchDTO swit){
        DadosExibicaoSwitchDTO dados = dispositivoInformaticaService.registrarDispositivoSwitch(swit);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-hub")
    @Operation(summary = "Registrar um hub")
    @Transactional
    public HttpResponse<DadosExibicaoHubDTO> registrarDispositivoHub(@Body DadosEntradaHubDTO hub){
        DadosExibicaoHubDTO dados = dispositivoInformaticaService.registrarDispositivoHub(hub);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Post(uri = "/registrar-monitor")
    @Operation(summary = "Registrar um monitor")
    @Transactional
    public HttpResponse<DadosExibicaoMonitorDTO> registrarDispositivoMonitor(@Body DadosEntradaMonitorDTO monitor){
        DadosExibicaoMonitorDTO dados = dispositivoInformaticaService.registrarDispositivoMonitor(monitor);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar")
    @Operation(summary = "Listar dispositivos")
    public HttpResponse<List<Object>> listarDispositivos(){
        List<Object> dispositivosEncontrados = dispositivoInformaticaService.listarDispositivos();
        return HttpResponse.ok().body(dispositivosEncontrados);
    }

//    @Put(uri = "/atualizar/{idDispositivo}")
//    @Operation(summary = "Atualizar um dispositivo")
//    @Transactional
//    public HttpResponse<DadosExibicaoDispositivoDTO> atualizarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo, @Body DadosAtualizacaoDispositivoDTO atualizacao){
//        DadosExibicaoDispositivoDTO dispositivo = dispositivoInformaticaService.atualizarDispositivo(idDispositivo, atualizacao);
//        return HttpResponse.ok().body(dispositivo);
//    }

    @Delete(uri = "/deletar/{idDispositivo}")
    @Operation(summary = "Deletar um dispositivo")
    @Transactional
    public HttpResponse deletarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo){
        dispositivoInformaticaService.deletarDispositivo(idDispositivo);
        return HttpResponse.ok();
    }

//    @Put(uri = "/manipular/{idDispositivo}")
//    @Transactional
//    @Operation(summary = "Desvincular/vincular um dispositivo a um leilao")
//    public HttpResponse manipularDispositivoLeilao(@PathVariable (value = "idDispositivo") Long idDispositivo, @QueryValue Long idNovoLeilao){
//        dispositivoInformaticaService.manipularDispostivoLeilao(idDispositivo, idNovoLeilao);
//        return HttpResponse.ok();
//    }
}

