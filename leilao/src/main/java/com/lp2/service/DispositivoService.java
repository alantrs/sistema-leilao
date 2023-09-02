package com.lp2.service;

import com.lp2.dominio.DispositivoInformatica;

import com.lp2.dto.DadosCadastroDispositivoInformatica;
import com.lp2.dto.DadosExibicaoDispositivoInformatica;
import com.lp2.repository.DispositivoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class DispositivoService {

    @Inject
    private DispositivoRepository dispositivoInformaticaRepository;

    public DadosExibicaoDispositivoInformatica salvarDispositivo(DadosCadastroDispositivoInformatica cadastro){
        DispositivoInformatica dispositivoInformatica = new DispositivoInformatica(cadastro);
        dispositivoInformaticaRepository.save(dispositivoInformatica);
        return new DadosExibicaoDispositivoInformatica(dispositivoInformatica);
    }

    public List<DadosExibicaoDispositivoInformatica> listarDispositivos(){
        List<DispositivoInformatica> dispositivosInformatica = dispositivoInformaticaRepository.findAll();
        return dispositivosInformatica.stream().map(dispositivo -> new DadosExibicaoDispositivoInformatica(dispositivo)).toList();
    }
}

