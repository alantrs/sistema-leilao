package com.lp2.controller;

import com.lp2.model.dispositivo.DadosAtualizacaoDispositivoDTO;
import com.lp2.model.dispositivo.DadosEntradaDispositivoDTO;
import com.lp2.model.dispositivo.DadosExibicaoDispositivoDTO;
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

    @Post(uri = "/registrar-um-dispositivo")
    @Operation(summary = "Registrar um dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivoDTO> registarDispositivo(@Body DadosEntradaDispositivoDTO dadosEntradaDispositivoDTO){
        DadosExibicaoDispositivoDTO dados = dispositivoInformaticaService.registrarDispositivo(dadosEntradaDispositivoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }


    @Get(uri = "/listar")
    @Operation(summary = "Listar dispositivos")
    public HttpResponse<List<DadosExibicaoDispositivoDTO>> listarDispositivos(){
        List<DadosExibicaoDispositivoDTO> dispositivosEncontrados = dispositivoInformaticaService.listarDispositivos();
        return HttpResponse.ok().body(dispositivosEncontrados);
    }

    @Put(uri = "/atualizar/{idDispositivo}")
    @Operation(summary = "Atualizar um dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivoDTO> atualizarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo, @Body DadosAtualizacaoDispositivoDTO atualizacao){
        DadosExibicaoDispositivoDTO dispositivo = dispositivoInformaticaService.atualizarDispositivo(idDispositivo, atualizacao);
        return HttpResponse.ok().body(dispositivo);
    }

    @Delete(uri = "/deletar/{idDispositivo}")
    @Operation(summary = "Deletar um dispositivo")
    @Transactional
    public HttpResponse deletarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo){
        dispositivoInformaticaService.deletarDispositivo(idDispositivo);
        return HttpResponse.ok();
    }

    @Put(uri = "/manipular/{idDispositivo}")
    @Transactional
    @Operation(summary = "Desvincular/vincular um dispositivo a um leilao")
    public HttpResponse manipularDispositivoLeilao(@PathVariable (value = "idDispositivo") Long idDispositivo, @QueryValue Long idLeilao){
        dispositivoInformaticaService.manipularDispostivoLeilao(idDispositivo, idLeilao);
        return HttpResponse.ok();
    }
}

