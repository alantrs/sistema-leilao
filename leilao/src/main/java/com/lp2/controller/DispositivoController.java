package com.lp2.controller;

import com.lp2.dto.DadosCadastroDispositivoInformatica;
import com.lp2.dto.DadosExibicaoDispositivoInformatica;
import com.lp2.service.DispositivoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller(value = "/")
@Tag(name = "DISPOSITIVO")
public class DispositivoController {

    @Inject
    private DispositivoService dispositivoInformaticaService;

    @Post(uri = "/criar", produces = APPLICATION_JSON)
    @Operation(summary = "Salvar dispositivo")
    @Transactional
    public HttpResponse<DadosExibicaoDispositivoInformatica> salvarDispositivo(@Body DadosCadastroDispositivoInformatica dadosCadastroDispositivoInformatica){
        DadosExibicaoDispositivoInformatica dados = dispositivoInformaticaService.salvarDispositivo(dadosCadastroDispositivoInformatica);
        return HttpResponse.status(HttpStatus.CREATED).body(dados);
    }

    @Get(uri = "/listar", produces = APPLICATION_JSON)
    @Operation(summary = "Listar dispositivos")
    public HttpResponse<List<DadosExibicaoDispositivoInformatica>> listarDispositivos(){
        List<DadosExibicaoDispositivoInformatica> dispositivosEncontrados = dispositivoInformaticaService.listarDispositivos();
        return HttpResponse.ok().body(dispositivosEncontrados);
    }
}

