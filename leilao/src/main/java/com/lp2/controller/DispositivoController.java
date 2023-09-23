package com.lp2.controller;

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

    @Post(uri = "/criar-um-dispositivo")
    @Operation(summary = "Salvar um dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivoDTO> salvarDispositivo(@Body DadosEntradaDispositivoDTO dadosEntradaDispositivoDTO){
        DadosExibicaoDispositivoDTO dados = dispositivoInformaticaService.salvarDispositivo(dadosEntradaDispositivoDTO);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }


    @Post(uri = "/criar-varios-dispositivos")
    @Operation(summary = "Salvar varios dispositivos")
    @Transactional
    public HttpResponse<List<DadosExibicaoDispositivoDTO>> salvarDispositivos(@Body List<DadosEntradaDispositivoDTO> cadastro){
        List<DadosExibicaoDispositivoDTO> dispositivosSalvos = dispositivoInformaticaService.salvarDispositivos(cadastro);
        return HttpResponse.status(HttpStatus.CREATED).body(dispositivosSalvos);
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
    public HttpResponse<DadosExibicaoDispositivoDTO> atualizarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo, @Body DadosEntradaDispositivoDTO dadosEntradaDispositivoDTO){
        DadosExibicaoDispositivoDTO dispositivo = dispositivoInformaticaService.atualizarDispositivo(idDispositivo, dadosEntradaDispositivoDTO);
        return HttpResponse.noContent().body(dispositivo);
    }

    @Delete(uri = "/deletar/{idDispositivo}")
    @Operation(summary = "Deletar um dispositivo")
    @Transactional
    public HttpResponse deletarDispositivo(@PathVariable (value = "idDispositivo") Long idDispositivo){
        dispositivoInformaticaService.deletarDispositivo(idDispositivo);
        return HttpResponse.noContent();
    }

    @Put(uri = "/vincular/{idDispositivo}")
    @Transactional
    @Operation(summary = "Vincular um dispositivo a um leilao")
    public HttpResponse vincularDispositivoLeilao(@PathVariable (value = "idDispositivo") Long idDispositivo, @RequestAttribute Long idLeilao){
        dispositivoInformaticaService.manipularDispostivoLeilao(idDispositivo, idLeilao);
        return HttpResponse.noContent();
    }
}

